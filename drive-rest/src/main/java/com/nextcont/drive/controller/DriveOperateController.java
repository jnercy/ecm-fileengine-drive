package com.nextcont.drive.controller;


import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.nextcont.drive.aspect.AuthAspect;
import com.nextcont.drive.jooq.bean.TransitionUnAggregationData;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.service.FileMetadataMaker;
import com.nextcont.drive.utils.*;
import com.nextcont.file.*;
import com.nextcont.file.request.file.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.nextcont.drive.mongo.MongoField.*;
import static com.nextcont.drive.utils.ResponseMaker.getErrorResponse;
import static com.nextcont.drive.utils.ResponseMaker.getSuccessResponse;
import static com.nextcont.drive.utils.TupleFactories.pairs;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/4
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/drive/v1/files")
@Slf4j
public class DriveOperateController {

    @Autowired
    private BaseMongoService driveMongoservice;

    @Autowired
    private IdGenService idGenService;

    @Autowired
    private FastFileStorageClient storageClient;



    @PostMapping(value="/move",produces = "application/json")
    public ResponseEntity<Object> move(@RequestBody FileMoveRequest[] request) {
        try {
            Stream.of(request).forEach(fileMoveInfo -> {
                        driveMongoservice
                                .queryOneFullField(mongoReady2MoveFileQuery(fileMoveInfo.getFileId(), fileMoveInfo.getSource()))
                                .ifPresent(driveFile -> {
                                    FileMetaData metaData = BeanUtil.toBean(driveFile, FileMetaData.class);
                                    List<UserRecord> userRecords = metaData.getUserRecords();
                                    userRecords.forEach(p -> {
                                        if (StringUtils.isNotEmpty(fileMoveInfo.getSource())) {
                                            if (p.getParents().get(0).equals(fileMoveInfo.getSource()))
                                                p.getParents().set(0, fileMoveInfo.getTarget());
                                        } else {
                                            if (p.getRootId().equals(AuthAspect.getAuthTokenInfo().getRootid()))
                                                p.getParents().add(fileMoveInfo.getTarget());
                                        }
                                    });
                                    BsonArray userRecordArray = new BsonArray();
                                    userRecords.forEach(p -> userRecordArray.add(BsonDocument.parse(JsonFormat.toJson(p))));
                                    boolean updateResult = driveMongoservice.updateOne(mongoDefaultQuery(fileMoveInfo.getFileId()), set("userRecords", userRecordArray));
                                    log.info("file move {}. fileId=>{}", updateResult ? "success" : "failed", fileMoveInfo.getFileId());
                                });
                    }

            );
            return new ResponseEntity<>(request,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/copy/{fileId}", produces = "application/json")
    public ResponseEntity<Object> copy(@PathVariable("fileId") String fileId,  @RequestBody FileRequestbody patchData) {

        Tuple<Object, HttpStatus> result = driveMongoservice
                .queryOneFullField(mongoDefaultQuery(fileId))
                .map(record -> {
                    record.put("_id", String.valueOf(idGenService.nextId()));
                    record.put("id", String.valueOf(idGenService.nextId()));
                    record.put("name",patchData.getName());
                    record.put("createTime",DateTimeUtil.nowTime2String());
                    record.put("modifiedTime",DateTimeUtil.nowTime2String());
                    driveMongoservice.insert(record);
                    return pairs(getSuccessResponse("insert success."), HttpStatus.OK);
                })
                .orElse(pairs(getErrorResponse("fileId not found . please check fileId isExsit"), HttpStatus.BAD_REQUEST));

        return new ResponseEntity<>(result.v1(), result.v2());

    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody FileCreateRequestBody data) {
        TransitionUnAggregationData.TransitionUnAggregationDataBuilder builder = TransitionUnAggregationData.builder();
        TransitionUnAggregationData createFileInfo = builder
                .fileId(StringUtils.isNotEmpty(data.getId()) ? data.getId() : String.valueOf(idGenService.nextId()))
                .fileName(data.getName())
                .mimeType(StringUtils.isNotEmpty(data.getMimeType()) ? data.getMimeType() : "")
                .parent(data.getParent())
                .permissionGenId(idGenService.nextId())
                .createTime(DateTime.now().toDate())
                .build();

        Document createFileDom = FileMetadataMaker.makeMetaData(createFileInfo);

        driveMongoservice.insert(createFileDom);

        return new ResponseEntity<>(createFileDom, HttpStatus.OK);
    }

    @DeleteMapping(value = "/trash/{fileId}", produces = "application/json")
    public ResponseEntity<Object> trash(@PathVariable("fileId") String fileId) {
        if (StringUtils.isNotEmpty(fileId)) {
            boolean updateStatus = driveMongoservice.updateMany(mongoUnlockPatchQuery(Arrays.asList(fileId.split(","))), set("trashed", true));
            Object result = updateStatus ? getSuccessResponse("trash success.") : getErrorResponse("trash error.");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else
            return new ResponseEntity<>(getErrorResponse("fileId is null or empty."), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/trash", produces = "application/json")
    public ResponseEntity<Object> emptyTrash() {
        try {
            driveMongoservice
                    .query(mongoTrashQuery(), driveFileExcludeField)
                    .forEach(records -> {
                        driveMongoservice.delete(mongoUnlockQuery(records.get("id").toString()));
                        log.info("delete file document=>",records.get("id"));
                        if (records.get("webContentLink")!= null && StringUtils.isNotEmpty(records.get("webContentLink").toString())){
                            log.info("delete file =>{}",records.get("webContentLink").toString());
                            storageClient.deleteFile(records.get("webContentLink").toString());
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(getSuccessResponse("trash success"), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{fileId}", produces = "application/json")
    public ResponseEntity<Object> deleteFile(@PathVariable("fileId") String fileId) {
        driveMongoservice
                .query(mongoUnlockPatchQuery(Arrays.asList(fileId.split(","))), driveFileExcludeField)
                .forEach(records -> driveMongoservice.delete(mongoUnlockQuery(records.get("id").toString())));

        return new ResponseEntity<>(getSuccessResponse("trash success"), HttpStatus.OK);
    }


    @PostMapping(value = "/lock", produces = "application/json")
    public ResponseEntity<Object> lock(@RequestBody FileLockRequest request) {
        try {
            Tuple<Object, HttpStatus> result = driveMongoservice.queryOneFullField(mongoUnlockQuery(request.getFileId()))
                    .map(record -> {
                        String unlockTime = new DateTime().plusSeconds(request.getQuantity()).toString("yyyy-MM-dd");
                        Bson updateLockInfo = request.isManuallyUnlock() ? set("manuallyUnlock", true) : set("deblockingTime", unlockTime);

                        boolean updateResult = driveMongoservice.updateOne(mongoDefaultQuery(request.getFileId()), combine(set("locked", true), updateLockInfo));
                        String status = updateResult ? "success" : "failed";
                        log.info("lock update status:{}", status);
                        return pairs(getSuccessResponse("lock update status:" + status), HttpStatus.OK);
                    })
                    .orElse(pairs(getErrorResponse("file not found or check failed"), HttpStatus.BAD_REQUEST));
            return new ResponseEntity<>(result.v1(), result.v2());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(getErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/metadata/{fileId}", produces = "application/json")
    public ResponseEntity<Object> modifyMetadata(@PathVariable("fileId") String fileId,@RequestBody FileRequestbody patchData) {

        boolean updateResult = false;
        try {
            Bson updateBson = mongoPatchMetadata(patchData);
            updateResult = driveMongoservice.updateMany(mongoUnlockPatchQuery(Arrays.asList(fileId.split(","))), updateBson);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (updateResult)
            return new ResponseEntity<>(getSuccessResponse("file metadata update success."), HttpStatus.OK);
        else
            return new ResponseEntity<>(getErrorResponse("file metadata update failed."), HttpStatus.BAD_REQUEST);

    }

}

package com.nextcont.drive.controller;


import com.nextcont.drive.aspect.AuthAspect;
import com.nextcont.drive.jooq.bean.TransitionUnAggregationData;
import com.nextcont.drive.mongo.MongoQuery;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.service.FileMetadataMaker;
import com.nextcont.drive.utils.IdGenService;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.StringUtils;
import com.nextcont.drive.utils.Tuple;
import com.nextcont.file.DriveFile;
import com.nextcont.file.FileList;
import com.nextcont.file.FileMetaData;
import com.nextcont.file.TokenInfo;
import com.nextcont.file.request.file.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.nextcont.drive.mongo.MongoField.driveFileExcludeField;
import static com.nextcont.drive.mongo.MongoField.excludeUsersRecords;
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
public class DriveController {

    @Autowired
    private BaseMongoService<DriveFile> driveFileService;

    @Autowired
    private BaseMongoService<FileMetaData> fileMetaDataService;

    @Autowired
    private IdGenService idGenService;

    @PostMapping(value = "/{fileId}/copy",produces = "application/json")
    public ResponseEntity<Object> copy(@PathVariable("fileId") String fileId, FileCopyRequest reuqest, @RequestBody FileRequestbody patchData) {

        Tuple<Object,HttpStatus> result = fileMetaDataService
                .queryOneFullField(new Document("id", fileId))
                .map(record -> {
                    record.setId(String.valueOf(idGenService.nextId()));
                    return JsonFormat.convertJson(record)
                            .map(insertJson -> {
                                Document doc = Document.parse(insertJson);
                                fileMetaDataService.insert(doc);
                                return pairs(getSuccessResponse("insert success."),HttpStatus.OK);
                            })
                            .orElse(pairs(getErrorResponse("parse error. please check FileMetaData bean!"),HttpStatus.BAD_REQUEST));
                })
                .orElse(pairs(getErrorResponse("fileId not found . please check fileId isExsit"),HttpStatus.BAD_REQUEST));

        return new ResponseEntity<>(result.v1(),result.v2());

    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<Object> create(FileCreateRequest request, @RequestBody FileCreateRequestBody data) {
        TransitionUnAggregationData.TransitionUnAggregationDataBuilder builder = TransitionUnAggregationData.builder();
        TransitionUnAggregationData createFileInfo = builder
                .fileId(data.getId())
                .fileName(data.getName())
                .mimeType(data.getMimeType())
                .parents(data.getParents())
                .permissionGenId(idGenService.nextId())
                .createTime(DateTime.now().toDate()).build();

        Document createFileDom = FileMetadataMaker.makeMetaData(createFileInfo);

        fileMetaDataService.insert(createFileDom);

        return new ResponseEntity<>(getSuccessResponse("file create success . "),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{fileId}",produces = "application/json")
    public ResponseEntity<Object> trash(@PathVariable("fileId") String fileId) {
        Bson queryBson = new Document("id",fileId);
        Bson trashBson = set("trashed",true);
        boolean updateStatus = fileMetaDataService.updateOne(queryBson,trashBson);

        Object result = updateStatus ? getSuccessResponse("trash success.") : getErrorResponse("trash error.");
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping(value = "/trash", produces = "application/json")
    public ResponseEntity<Object> emptyTrash() {
        fileMetaDataService
                .query(new Document("trashed",true),driveFileExcludeField)
                .forEach(records-> fileMetaDataService.delete(new Document("id",records.getId())));

        return new ResponseEntity<>(getSuccessResponse("trash success"),HttpStatus.OK);
    }

    @GetMapping(value = "/{fileId}/export",  produces = "application/json")
    public String export(@PathVariable("fileId") String fileId, @RequestParam("mimeType") String mimeType) {
        return "";
    }

    @GetMapping(value = "/generateIds")
    public Object generateIds(HttpServletRequest request) {
        return idGenService.nextId();
    }

    @GetMapping(value = "/{fileId}",produces = "application/json")
    public ResponseEntity<Object> get(HttpServletRequest request, @PathVariable("fileId") String fileId) {

        Document queryBson = new Document("parents",AuthAspect.getAuthTokenInfo().getRootid())
                .append("id",fileId);
        DriveFile result = driveFileService
                .queryOne(queryBson, driveFileExcludeField)
                .orElse(null);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @GetMapping(value = "/list",produces = "application/json")
    public ResponseEntity<Object> list(HttpServletRequest request, FileListRequest fileListRequest) {
        Document queryBson = new Document("parents",AuthAspect.getAuthTokenInfo().getRootid());

        Arrays.stream(fileListRequest.getQ().split("and"))
            .forEach(queryParam->{
                queryParam = queryParam.trim();

                if(queryParam.contains("trashed")) {
                    if (queryParam.contains("true"))
                        queryBson.append("trashed", true);
                    else
                        queryBson.append("trashed", false);
                }
                else if(queryParam.contains("sharedWithMe")){

                    queryBson.append("permissions.emailAdddress",AuthAspect.getAuthTokenInfo().getGmail());

                    if(queryParam.contains("true"))
                        queryBson.append("permissions.role",new Document("$ne","owner"));
                    else if(queryParam.contains("false")){
                        queryBson.append("permissions.role","owner");
                    }
                }
                else if(queryParam.contains("starred")){
                    if (queryParam.contains("true"))
                        queryBson.append("starred", true);
                    else
                        queryBson.append("starred", false);
                }
                else if(queryParam.contains("ownedByMe")){
                    if (queryParam.contains("true"))
                        queryBson.append("ownedByMe", true);
                    else
                        queryBson.append("ownedByMe", false);
                }
            });



        MongoQuery query = MongoQuery
                .builder()
                .queryBson(queryBson)
                .pageSize(fileListRequest.getPageSize())
                .pageToken(fileListRequest.getPageToken())
                .projection(driveFileExcludeField)
                .build();

        Long totalCount = driveFileService.count(queryBson);

        List<DriveFile> driveFiles = driveFileService.queryFileList(query);

        FileList<DriveFile> result = new FileList<>();

        result.setFiles(driveFiles);

        result.setNextPageToken(totalCount<fileListRequest.getPageSize()*fileListRequest.getPageToken() ? 0 : fileListRequest.getPageToken() + 1);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping(value = "/lock",produces = "application/json")
    public ResponseEntity<Object> lock(@RequestBody FileLockRequest request) {
        try {
            Tuple<Object,HttpStatus> result = fileMetaDataService.queryOneFullField(new Document("owners.emailAddress", request.getUserId())
                    .append("id", request.getFileId())
                    .append("locked", false))
                    .map(record -> {
                        String unlockTime = new DateTime().plusSeconds(request.getQuantity()).toString("yyyy-MM-dd");
                        Bson updateLockInfo = request.isManuallyUnlock() ? set("manuallyUnlock", true) : set("deblockingTime", unlockTime);

                        boolean updateResult = fileMetaDataService.updateOne(new Document("id", request.getFileId()), combine(set("locked", true), updateLockInfo));
                        String status = updateResult ? "success" : "failed";
                        log.info("lock update status:{}", status);
                        return pairs(getSuccessResponse("lock update status:" + status),HttpStatus.OK);
                    })
                    .orElse(pairs(getErrorResponse("file not found or check failed"),HttpStatus.BAD_REQUEST));
            return new ResponseEntity<>(result.v1(),result.v2());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(getErrorResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/metadata/{fileId}",produces = "application/json")
    public ResponseEntity<Object> getMetadata(@PathVariable("fileId") String fileId) {
        log.info("[/{}/metadata][method:{}]", fileId, "get");
        Object result = fileMetaDataService.queryOne(new Document("id", fileId), excludeUsersRecords).orElse(null);
        return result !=null ? new ResponseEntity<>(result,HttpStatus.OK) : new ResponseEntity<>(getErrorResponse("fileId not found."),HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(value = "/metadata/{fileId}",produces = "application/json")
    public ResponseEntity<Object> modifyMetadata(@PathVariable("fileId") String fileId, FileMetadataRequest request, @RequestBody FileRequestbody patchData) {
        log.info("[/{}/metadata][method:patch]", "");

        return fileMetaDataService
                .queryOne(new Document("id", fileId).append("locked", false), excludeUsersRecords)
                .map(fileMetaData -> {
                    List<Bson> updateBsons = new ArrayList<>();
                    if (StringUtils.isNotEmpty(request.getAddParents())) {
//                        String[] parentList = fileMetaData.getParents();
//
//                        parentList.add(request.getAddParents());
//                        updateBsons.add(set("parents", parentList));
                    } else if (StringUtils.isNotEmpty(request.getRemoveParents())) {
//                        List<String> parentList = fileMetaData.getParents()
//                                .stream()
//                                .filter(parentId -> !parentId.equals(request.getRemoveParents()))
//                                .collect(Collectors.toList());
//                        updateBsons.add(set("parents", parentList));
                    } else if (patchData.getAppProperties() != null)
                        updateBsons.add(set("appProperties", patchData.getAppProperties()));

                    else if (patchData.getContentHints() != null)
                        updateBsons.add(set("contentHints", patchData.getContentHints()));

                    else if (patchData.getDescription() != null)
                        updateBsons.add(set("description", patchData.getDescription()));

                    else if (patchData.getFolderColorRgb() != null)
                        updateBsons.add(set("folderColorRgb", patchData.getFolderColorRgb()));

                    else if (patchData.getMimeType() != null)
                        updateBsons.add(set("mimeType", patchData.getMimeType()));

                    else if (patchData.getModifiedTime() != null)
                        updateBsons.add(set("modifiedTime", patchData.getModifiedTime()));

                    else if (patchData.getName() != null)
                        updateBsons.add(set("name", patchData.getName()));

                    else if (patchData.getProperties() != null)
                        updateBsons.add(set("properties", patchData.getProperties()));

                    else if (StringUtils.isNotEmpty(patchData.getStarred()))
                        updateBsons.add(set("starred", patchData.getStarred().equals("true")));

                    else if (StringUtils.isNotEmpty(patchData.getTrashed()))
                        updateBsons.add(set("trashed", patchData.getTrashed().equals("true")));
                    else if (StringUtils.isNotEmpty(patchData.getViewedByMeTime()))
                        updateBsons.add(set("viewedByMeTime", patchData.getViewedByMeTime()));

                    else if (StringUtils.isNotEmpty(patchData.getViewersCanCopyContent()))
                        updateBsons.add(set("viewersCanCopyContent", patchData.getViewersCanCopyContent()
                                .equals("true")));

                    else if (StringUtils.isNotEmpty(patchData.getWritersCanShare()))
                        updateBsons.add(set("writersCanShare", patchData.getWritersCanShare().equals("true")));

                    Bson updateBson = combine(updateBsons);

                    boolean updateResult = fileMetaDataService.updateOne(new Document("id", fileMetaData.getId()), updateBson);

                    if (updateResult)
                        return new ResponseEntity<>(getSuccessResponse("file metadata update success."),HttpStatus.OK);
                    else
                        return new ResponseEntity<>(getErrorResponse("file metadata update failed."),HttpStatus.BAD_REQUEST);
                })
                .orElse(new ResponseEntity<>(getErrorResponse("file not found or check failed."),HttpStatus.BAD_REQUEST));
    }

}

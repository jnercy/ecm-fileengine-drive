package com.nextcont.drive.controller;


import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.Try;
import com.nextcont.file.*;
import com.nextcont.file.request.FileListRequest;
import com.nextcont.file.request.FileLockRequest;
import com.nextcont.file.request.FileShareRequest;
import com.nextcont.file.request.PatchMetadataReqeust;
import com.nextcont.file.response.ExecutionRecord;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;
import static com.nextcont.drive.mongo.MongoField.driveFileExcludeField;
import static com.nextcont.drive.mongo.MongoField.excludeUsersRecords;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/4
 * Time: 17:04
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/files")
@Slf4j
public class DriveController {

    @Autowired
    private BaseMongoService<DriveFile> driveFileService;

    @Autowired
    private BaseMongoService<FileMetaData> fileMetaDataService;


    private final String DEMO_USER_ID = "jnercywang@gmail.com";


    @RequestMapping(value = "/get/{fileId}", method = RequestMethod.GET)
    public DriveFile get(@PathVariable("fileId") String fileId) {

        DriveFile result = driveFileService
                .queryOne(eq("id", fileId), driveFileExcludeField)
                .orElse(null);

        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public FileList<DriveFile> list(@RequestBody FileListRequest request) {

        List<DriveFile> driveFiles = driveFileService.queryFileList(request);

        FileList<DriveFile> result = new FileList<>();

        result.setFiles(driveFiles);

        result.setNextPageToken(request.getPageToken() + 1);

        return result;
    }

    @RequestMapping(value = "/lock", method = RequestMethod.POST)
    public String lock(@RequestBody FileLockRequest request) {
        try {
            fileMetaDataService.queryOneFullField(new Document("owners.emailAddress", request.getUserId())
                    .append("id", request.getFileId())
                    .append("locked", false))
                    .ifPresent(record -> {
                        String unlockTime = new DateTime().plusSeconds(request.getQuantity()).toString("yyyy-MM-dd");
                        Bson updateLockInfo = request.isManuallyUnlock() ? set("manuallyUnlock", true) : set("deblockingTime", unlockTime);

                        boolean updateResult = fileMetaDataService.updateOne(new Document("id", request.getFileId()), combine(set("locked", true), updateLockInfo));
                        log.info("lock update status:{}", updateResult ? "success" : "failed");
                    });
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }


    //分享逻辑需要梳理
    @RequestMapping(value = "/sharing", method = RequestMethod.POST)
    public String share(@RequestBody FileShareRequest request) {
        String proccessRecord =
                fileMetaDataService.queryOneFullField(new Document("owners.emailAddress", DEMO_USER_ID).append("id", request.getFileId()))
                .map(driveFile -> {
                            Try<String> proccessTry = Try.tried(driveFile, file -> {
                                BsonArray permissionArray = new BsonArray();
                                BsonArray recordsArray = new BsonArray();
                                List<FilePermission> permissions = file.getPermissions();
                                FilePermission permission = FilePermission.builder()
                                        .id(request.getTargetUserId())
                                        .type("user")
                                        .emailAdddress(request.getTargetUserId())
                                        .photoLink("")
                                        .displayName(request.getTargetUserId())
                                        .role(request.getRole())
                                        .build();
                                permissions.add(permission);

                                permissions.forEach(p -> permissionArray.add(BsonDocument.parse(JsonFormat.toJson(p))));

                                List<FileProcessRecord> records = file.getUserRecords();

                                FileProcessRecord.FileProcessRecordBuilder recordBuilder = FileProcessRecord.builder();
                                FileProcessRecord processRecord = recordBuilder
                                        .userId(request.getTargetUserId())
                                        .ownedByMe(false)
                                        .modifyByMe(false)
                                        .starred(false)
                                        .build();
                                records.add(processRecord);

                                records.forEach(r -> recordsArray.add(BsonDocument.parse(JsonFormat.toJson(r))));
                                Bson combineUpdate = combine(set("permissions", permissionArray), set("userRecords", recordsArray), inc("version", 1));
                                boolean updateResult =
                                        driveFileService
                                                .updateOne(new Document("id", request.getFileId()), combineUpdate);
                                log.info("share update status:{}", updateResult ? "success" : "failed");
                                return "sharing success";
                            });
                            return proccessTry.isSuccess()  ? getSuccessResponse(proccessTry.getOrThrow()) : getErrorResponse(proccessTry.getOrThrow());
                        }
                ).orElse(getErrorResponse("file not found or check failed"));

        return proccessRecord;
    }


    @RequestMapping(value = "/metadata/{fileId}", method = RequestMethod.GET)
    public FileMetaData getMetadata(@PathVariable("fileId") String fileId) {
        log.info("[/{}/metadata][method:{}]", fileId, "get");
        return fileMetaDataService.queryOne(new Document("id", fileId), excludeUsersRecords).orElse(null);
    }

    @RequestMapping(value = "/metadata", method = RequestMethod.PATCH)
    public String modifyMetadata(@RequestBody PatchMetadataReqeust data) {
        log.info("[/{}/metadata][method:{}]", data.getFileId(), "patch");
        return getSuccessResponse("Optional function");
    }


    public String getSuccessResponse(String message) {
        return JsonFormat.convertJson(ExecutionRecord.generateSuccessResponse(message)).orElse(message);
    }

    public String getErrorResponse(String message) {
        return JsonFormat.convertJson(ExecutionRecord.generateErrorResponse(message)).orElse(message);
    }


}

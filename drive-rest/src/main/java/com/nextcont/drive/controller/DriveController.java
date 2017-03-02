package com.nextcont.drive.controller;


import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.StringUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            String result = fileMetaDataService.queryOneFullField(new Document("owners.emailAddress", request.getUserId())
                    .append("id", request.getFileId())
                    .append("locked", false))
                    .map(record -> {
                        String unlockTime = new DateTime().plusSeconds(request.getQuantity()).toString("yyyy-MM-dd");
                        Bson updateLockInfo = request.isManuallyUnlock() ? set("manuallyUnlock", true) : set("deblockingTime", unlockTime);

                        boolean updateResult = fileMetaDataService.updateOne(new Document("id", request.getFileId()), combine(set("locked", true), updateLockInfo));
                        String status = updateResult ? "success" : "failed";
                        log.info("lock update status:{}", status);
                        return getSuccessResponse("lock update status:" + status);
                    })
                    .orElse(getErrorResponse("file not found or check failed"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorResponse(e.getMessage());
        }
    }


    //分享逻辑需要梳理
    @RequestMapping(value = "/sharing", method = RequestMethod.POST)
    public String share(@RequestBody FileShareRequest request) {
        String proccessRecord = fileMetaDataService
                        .queryOneFullField(new Document("owners.emailAddress", DEMO_USER_ID).append("id", request.getFileId()).append("locked", false))
                        .map(driveFile -> {
                                    Try<String> proccessTry = Try.tried(driveFile, file -> {
                                        BsonArray permissionArray = new BsonArray();
                                        BsonArray recordsArray = new BsonArray();
                                        List<FilePermission> permissions = file.getPermissions();
                                        FilePermission permission = FilePermission.builder()
                                                .id(request.getTargetUserId())
                                                .type("user")
                                                .emailAdddress(request.getTargetUserId())
                                                .photoLink("/.jpg")
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
                                    return proccessTry.isSuccess() ? getSuccessResponse(proccessTry.getOrThrow()) : getErrorResponse(proccessTry
                                            .getOrThrow());
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
    public String modifyMetadata(HttpServletRequest request, @RequestBody PatchMetadataReqeust patchData) {
        log.info("[/{}/metadata][method:patch]", "");
        String fileId = request.getParameter("fileId");
        return fileMetaDataService
                .queryOne(new Document("id", fileId).append("locked",false), excludeUsersRecords)
                .map(fileMetaData -> {
                    List<Bson> updateBsons = new ArrayList<>();
                    if(StringUtils.isNotEmpty(request.getParameter("addParents"))){
                        List<String> parentList = fileMetaData.getParents();
                        parentList.add(request.getParameter("addParents"));
                        updateBsons.add(set("parents",parentList));
                    }
                    else if(StringUtils.isNotEmpty(request.getParameter("removeParents"))){
                        List<String> parentList = fileMetaData.getParents()
                                .stream()
                                .filter(parentId-> !parentId.equals(request.getParameter("removeParents")))
                                .collect(Collectors.toList());
                        updateBsons.add(set("parents",parentList));
                    }

                    else if(patchData.getAppProperties()!=null)
                        updateBsons.add(set("appProperties",patchData.getAppProperties()));

                    else if(patchData.getContentHints()!=null)
                        updateBsons.add(set("contentHints",patchData.getContentHints()));

                    else if(patchData.getDescription()!=null)
                        updateBsons.add(set("description",patchData.getDescription()));

                    else if(patchData.getFolderColorRgb()!=null)
                        updateBsons.add(set("folderColorRgb",patchData.getFolderColorRgb()));

                    else if(patchData.getMimeType()!=null)
                        updateBsons.add(set("mimeType",patchData.getMimeType()));

                    else if(patchData.getModifiedTime()!=null)
                        updateBsons.add(set("modifiedTime",patchData.getModifiedTime()));

                    else if(patchData.getName()!=null)
                        updateBsons.add(set("name",patchData.getName()));

                    else if(patchData.getProperties()!=null)
                        updateBsons.add(set("properties",patchData.getProperties()));

                    else if(StringUtils.isNotEmpty(patchData.getStarred()))
                        updateBsons.add(set("starred",patchData.getStarred().equals("true")));

                    else if(StringUtils.isNotEmpty(patchData.getTrashed()))
                        updateBsons.add(set("trashed",patchData.getTrashed().equals("true")));

                    else if(StringUtils.isNotEmpty(patchData.getViewedByMeTime()))
                        updateBsons.add(set("viewedByMeTime",patchData.getViewedByMeTime()));

                    else if(StringUtils.isNotEmpty(patchData.getViewersCanCopyContent()))
                        updateBsons.add(set("viewersCanCopyContent",patchData.getViewersCanCopyContent().equals("true")));

                    else if(StringUtils.isNotEmpty(patchData.getWritersCanShare()))
                        updateBsons.add(set("writersCanShare",patchData.getWritersCanShare().equals("true")));

                    Bson updateBson = combine(updateBsons);

                    boolean updateResult = fileMetaDataService.updateOne(new Document("id", fileMetaData.getId()),updateBson);

                    if(updateResult)
                        return getSuccessResponse("file metadata update success");
                    else
                        return getSuccessResponse("file metadata update failed");
                })
                .orElse(getErrorResponse("file not found or check failed"));
    }


    public String getSuccessResponse(String message) {
        return JsonFormat.convertJson(ExecutionRecord.generateSuccessResponse(message)).orElse(message);
    }

    public String getErrorResponse(String message) {
        return JsonFormat.convertJson(ExecutionRecord.generateErrorResponse(message)).orElse(message);
    }


}

package com.nextcont.drive.controller;

import com.nextcont.drive.mongo.MongoInnerDomQuery;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.utils.IdGenService;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.Try;
import com.nextcont.file.*;
import com.nextcont.file.request.permission.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;
import static com.nextcont.drive.utils.ResponseMaker.getErrorResponse;
import static com.nextcont.drive.utils.ResponseMaker.getSuccessResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/drive/v1/files")
@Slf4j
public class PermissionsController {


    @Autowired
    private BaseMongoService<FileMetaData> fileMetaDataService;

    @Autowired
    private BaseMongoService<FilePermission> permissionService;

    @Autowired
    private BaseMongoService<DriveFile> driveFileService;

    @Autowired
    private IdGenService idGenService;




    @RequestMapping(value = "/{fileId}/permissions", method = RequestMethod.POST)
    public String create(@PathVariable("fileId") String fileId, PermissionCreateRequest request, @RequestBody PermissionCreateRequestbody bodyData){
        String proccessRecord = fileMetaDataService
                .queryOneFullField(new Document("owners.emailAddress", "jnercywang@gmail.com").append("id",fileId)
                        .append("locked", false))
                .map(driveFile -> {
                            Try<String> proccessTry = Try.tried(driveFile, file -> {
                                BsonArray permissionArray = new BsonArray();
                                BsonArray recordsArray = new BsonArray();
                                List<FilePermission> permissions = file.getPermissions();
                                FilePermission permission = FilePermission.builder()
                                        .id(String.valueOf(idGenService.nextId()))
                                        .type("user")
                                        .emailAdddress(bodyData.getEmailAddress())
                                        .photoLink("noraml.jpg")
                                        .displayName(bodyData.getEmailAddress())
                                        .role(bodyData.getRole())
                                        .build();
                                permissions.add(permission);

                                permissions.forEach(p -> permissionArray.add(BsonDocument.parse(JsonFormat.toJson(p))));

                                List<FileProcessRecord> records = file.getUserRecords();

                                FileProcessRecord.FileProcessRecordBuilder recordBuilder = FileProcessRecord.builder();
                                FileProcessRecord processRecord = recordBuilder
                                        .userId(bodyData.getEmailAddress())
                                        .ownedByMe(false)
                                        .modifyByMe(false)
                                        .starred(false)
                                        .build();
                                records.add(processRecord);

                                records.forEach(r -> recordsArray.add(BsonDocument.parse(JsonFormat.toJson(r))));
                                Bson combineUpdate = combine(set("permissions", permissionArray), set("userRecords", recordsArray), inc("version", 1));
                                boolean updateResult =
                                        driveFileService
                                                .updateOne(new Document("id", fileId), combineUpdate);
                                log.info("share update status:{}", updateResult ? "success" : "failed");
                                return "sharing success";
                            });
                            return proccessTry.isSuccess() ? getSuccessResponse(proccessTry.getOrThrow()) : getErrorResponse(proccessTry
                                    .getOrThrow());
                        }
                ).orElse(getErrorResponse("file not found or check failed"));

        return proccessRecord;
    }


    @RequestMapping(value = "/{fileId}/permissions/{permissionId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("fileId") String fileId,@PathVariable String permissionId, @RequestParam boolean supportsTeamDrives){
        String proccessRecord = fileMetaDataService
                .queryOneFullField(new Document("owners.emailAddress", "jnercywang@gmail.com").append("id",fileId)
                        .append("locked", false))
                .map(driveFile -> {
                            List<FilePermission> permissions = driveFile.getPermissions();
                            boolean hasPermissionId = permissions.removeIf(p-> p.getId().equals(permissionId));
                            if(hasPermissionId){
                                BsonArray permissionArray = new BsonArray();
                                permissions.forEach(p -> permissionArray.add(BsonDocument.parse(JsonFormat.toJson(p))));
                                boolean deleteResult = driveFileService.updateOne(new Document("id",fileId),set("permissions", permissionArray));
                                return deleteResult ? "delete execute success!" : "delete execute failed!";
                            }else
                                return "permission Not found";
                        }
                ).orElse(getErrorResponse("file not found or check failed"));
        return proccessRecord;
    }

    @RequestMapping(value = "/{fileId}/permissions/{permissionId}", method = RequestMethod.GET)
    public FilePermission get(@PathVariable("fileId") String fileId,@PathVariable String permissionId, @RequestParam boolean supportsTeamDrives){
        MongoInnerDomQuery.MongoInnerDomQueryBuilder builder = MongoInnerDomQuery.builder();
        MongoInnerDomQuery query = builder
                .parentQuery(eq("id",fileId))
                .innerDomQuery(eq("permissions.id", permissionId))
                .innerFieldName("permissions")
                .build();
        FilePermission permissions = permissionService
                .queryInnerDocument(query)
                .orElse(null);
        return permissions;
    }

    @RequestMapping(value = "/{fileId}/permissions", method = RequestMethod.GET)
    public List<FilePermission> list(@PathVariable("fileId") String fileId, PermissionListRequest request){
        List<FilePermission> permissions = fileMetaDataService
                .queryOneFullField(new Document("id",fileId))
                .map(FileMetaData::getPermissions).orElse(null);
        return permissions;
    }


    @RequestMapping(value = "/{fileId}/permissions/{permissionId}", method = RequestMethod.PATCH)
    public String update(@PathVariable("fileId") String fileId, @PathVariable String permissionId, PermissionUpdateRequest request, @RequestBody PermissionUpdateRequestbody bodyData){
        String proccessRecord = fileMetaDataService
                .queryOneFullField(new Document("owners.emailAddress", "jnercywang@gmail.com").append("id",fileId)
                        .append("locked", false))
                .map(driveFile -> {
                            List<FilePermission> permissions = driveFile.getPermissions();
                            permissions.forEach(p->{
                                if(p.getId().equals(permissionId))
                                    p.setRole(bodyData.getRole());
                            });
                            BsonArray permissionArray = new BsonArray();
                            permissions.forEach(p -> permissionArray.add(BsonDocument.parse(JsonFormat.toJson(p))));
                            boolean updateResult = driveFileService.updateOne(new Document("id",fileId),set("permissions", permissionArray));
                            return updateResult ? "update execute success!" : "update execute failed!";
                        }
                ).orElse(getErrorResponse("file not found or check failed"));
        return proccessRecord;
    }
}

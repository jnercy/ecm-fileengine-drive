package com.nextcont.drive.controller;

import com.mongodb.client.model.Filters;
import com.nextcont.drive.aspect.AuthAspect;
import com.nextcont.drive.mongo.MongoInnerDomQuery;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.utils.IdGenService;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.Try;
import com.nextcont.drive.utils.Tuple;
import com.nextcont.file.*;
import com.nextcont.file.request.permission.*;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;
import static com.nextcont.drive.utils.ResponseMaker.getErrorResponse;
import static com.nextcont.drive.utils.ResponseMaker.getSuccessResponse;
import static com.nextcont.drive.utils.TupleFactories.pairs;

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


    @PostMapping(value = "/{fileId}/permissions", produces = "application/json")
    public ResponseEntity<Object> create(@PathVariable("fileId") String fileId, PermissionCreateRequest request, @RequestBody PermissionCreateRequestbody bodyData){
        TokenInfo tokenInfo = AuthAspect.getAuthTokenInfo();

        Bson queryBson = Filters.or(
                and(
                        eq("owners.emailAddress",tokenInfo.getGmail()),
                        eq("id",fileId),
                        eq("locked",false)),
                and(
                        eq("permissions.emailAddress",tokenInfo.getGmail()),
                        eq("permissions.role","writer"),
                        eq("id",fileId),eq("locked",false))
                );


        Tuple<Object,HttpStatus> result  = fileMetaDataService
                .queryOneFullField(queryBson)
                .map(driveFile -> {
                            Try<String> proccessTry = Try.tried(driveFile, file -> {
                                BsonArray permissionArray = new BsonArray();
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

                                Bson combineUpdate = combine(set("permissions", permissionArray),inc("version", 1));

                                boolean updateResult = driveFileService.updateOne(new Document("id", fileId), combineUpdate);
                                log.info("share update status:{}", updateResult ? "success" : "failed");
                                return "sharing success";
                            });
                            return proccessTry.isSuccess() ? pairs(getSuccessResponse(proccessTry.getOrThrow()),HttpStatus.OK) : pairs(getErrorResponse(proccessTry.getOrThrow()),HttpStatus.BAD_REQUEST);
                        }
                ).orElse(pairs(getErrorResponse("file not found or check failed"),HttpStatus.BAD_REQUEST));

        return new ResponseEntity<>(result.v1(),result.v2());
    }


    @DeleteMapping(value = "/{fileId}/permissions/{permissionId}", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable("fileId") String fileId,@PathVariable String permissionId, @RequestParam boolean supportsTeamDrives){

        Tuple<Object,HttpStatus> result = fileMetaDataService
                .queryOneFullField(new Document("owners.emailAddress", "jnercywang@gmail.com").append("id",fileId).append("locked", false))
                .map(driveFile -> {
                            List<FilePermission> permissions = driveFile.getPermissions();
                            boolean hasPermissionId = permissions.removeIf(p-> Objects.equals(p.getId(), permissionId));
                            if(hasPermissionId){
                                BsonArray permissionArray = new BsonArray();
                                permissions.forEach(p -> permissionArray.add(BsonDocument.parse(JsonFormat.toJson(p))));
                                boolean deleteResult = driveFileService.updateOne(new Document("id",fileId),set("permissions", permissionArray));
                                return deleteResult ? pairs(getSuccessResponse("delete execute success!"),HttpStatus.OK) : pairs(getErrorResponse("delete execute failed!"),HttpStatus.BAD_REQUEST);
                            }else
                                return pairs(getErrorResponse("permission Not found"),HttpStatus.BAD_REQUEST);
                        }
                ).orElse(pairs(getErrorResponse("file not found or check failed"),HttpStatus.BAD_REQUEST));

        return new ResponseEntity<>(result.v1(),result.v2());
    }

    @GetMapping(value = "/{fileId}/permissions/{permissionId}", produces = "application/json")
    public ResponseEntity<Object> get(@PathVariable("fileId") String fileId,@PathVariable String permissionId, @RequestParam boolean supportsTeamDrives){
        MongoInnerDomQuery.MongoInnerDomQueryBuilder builder = MongoInnerDomQuery.builder();
        MongoInnerDomQuery query = builder
                .parentQuery(eq("id",fileId))
                .innerDomQuery(eq("permissions.id", permissionId))
                .innerFieldName("permissions")
                .build();
        FilePermission permissions = permissionService
                .queryInnerDocument(query)
                .orElse(null);
        return new ResponseEntity<>(permissions,HttpStatus.OK);
    }

    @GetMapping(value = "/{fileId}/permissions",produces = "application/json")
    public ResponseEntity<?> list(@PathVariable("fileId") String fileId, PermissionListRequest request){
        List<FilePermission> permissions = fileMetaDataService
                .queryOneFullField(new Document("id",fileId))
                .map(FileMetaData::getPermissions).orElse(null);
        return new ResponseEntity<>(permissions,HttpStatus.OK);
    }


    @PatchMapping(value = "/{fileId}/permissions/{permissionId}", produces = "application/json")
    public ResponseEntity<Object> update(@PathVariable("fileId") String fileId, @PathVariable String permissionId, PermissionUpdateRequest request, @RequestBody PermissionUpdateRequestbody bodyData){
        Tuple<Object,HttpStatus> result = fileMetaDataService
                .queryOneFullField(new Document("owners.emailAddress", "jnercywang@gmail.com").append("id",fileId).append("locked", false))
                .map(driveFile -> {
                            List<FilePermission> permissions = driveFile.getPermissions();
                            permissions.forEach(p->{
                                if(p.getId().equals(permissionId))
                                    p.setRole(bodyData.getRole());
                            });
                            BsonArray permissionArray = new BsonArray();
                            permissions.forEach(p -> permissionArray.add(BsonDocument.parse(JsonFormat.toJson(p))));
                            boolean updateResult = driveFileService.updateOne(new Document("id",fileId),set("permissions", permissionArray));
                            return updateResult ? pairs(getSuccessResponse("update execute success!"),HttpStatus.OK) : pairs(getErrorResponse("update execute failed!"),HttpStatus.BAD_REQUEST);
                        }
                ).orElse(pairs(getErrorResponse("file not found or check failed"),HttpStatus.BAD_REQUEST));

        return new ResponseEntity<>(result.v1(),result.v2());
    }
}

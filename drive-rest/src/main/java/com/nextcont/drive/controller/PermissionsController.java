package com.nextcont.drive.controller;

import com.nextcont.drive.mongo.MongoInnerDomQuery;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.utils.*;
import com.nextcont.file.*;
import com.nextcont.file.request.permission.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;
import static com.nextcont.drive.mongo.MongoField.mongoCreatePermissionQuery;
import static com.nextcont.drive.mongo.MongoField.mongoDefaultQuery;
import static com.nextcont.drive.mongo.MongoField.mongoUnlockQuery;
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
    private BaseMongoService driveMongoService;

    @Autowired
    private IdGenService idGenService;


    @PostMapping(value = "/{fileId}/permissions", produces = "application/json")
    public ResponseEntity<Object> createPermissions(@PathVariable("fileId") String fileId,@RequestBody PermissionCreateRequestbody bodyData){

        Bson queryBson = mongoCreatePermissionQuery(fileId,bodyData.getEmailAddress());

        Tuple<Object,HttpStatus> result  = driveMongoService
                .queryOneFullField(queryBson)
                .map(driveFile -> {
                            Try<String> proccessTry = Try.tried(driveFile, file -> {
                                FileMetaData metaData = BeanUtil.toBean(driveFile,FileMetaData.class);

                                BsonArray permissionArray = new BsonArray();
                                List<FilePermission> permissions = metaData.getPermissions();
                                FilePermission permission = FilePermission.buildFilePermission(idGenService.nextId(),bodyData.getRole(),bodyData.getEmailAddress(),bodyData.getType());
                                permissions.add(permission);
                                permissions.forEach(p -> permissionArray.add(BsonDocument.parse(JsonFormat.toJson(p))));
                                Bson combineUpdate = combine(
                                        set("permissions", permissionArray),
                                        set("shared", true),
                                        inc("version", 1));

                                boolean updateResult = driveMongoService.updateMany(mongoDefaultQuery(fileId), combineUpdate);
                                log.info("share update status:{}", updateResult ? "success" : "failed");
                                return "sharing success";
                            });
                            return proccessTry.isSuccess() ? pairs(getSuccessResponse(proccessTry.getOrThrow()),HttpStatus.OK) : pairs(getErrorResponse(proccessTry.getOrThrow()),HttpStatus.BAD_REQUEST);
                        }
                ).orElse(pairs(getErrorResponse("file not found or check failed"),HttpStatus.BAD_REQUEST));

        return new ResponseEntity<>(result.v1(),result.v2());
    }


    @DeleteMapping(value = "/{fileId}/permissions/{permissionId}", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable("fileId") String fileId,@PathVariable String permissionId){

        Tuple<Object,HttpStatus> result = driveMongoService
                .queryOneFullField(mongoUnlockQuery(fileId))
                .map(driveFile -> {
                            FileMetaData metaData = BeanUtil.toBean(driveFile,FileMetaData.class);
                            List<FilePermission> permissions = metaData.getPermissions();
                            boolean hasPermissionId = permissions.removeIf(p->p.getId().equals(permissionId));
                            if(hasPermissionId){
                                BsonArray permissionArray = new BsonArray();
                                permissions.forEach(p -> permissionArray.add(BsonDocument.parse(JsonFormat.toJson(p))));
                                boolean deleteResult = driveMongoService.updateOne(new Document("id",fileId),set("permissions", permissionArray));
                                return deleteResult ? pairs(getSuccessResponse("delete execute success!"),HttpStatus.OK) : pairs(getErrorResponse("delete execute failed!"),HttpStatus.BAD_REQUEST);
                            }else
                                return pairs(getErrorResponse("permission Not found"),HttpStatus.BAD_REQUEST);
                        }
                ).orElse(pairs(getErrorResponse("file not found or check failed"),HttpStatus.BAD_REQUEST));

        return new ResponseEntity<>(result.v1(),result.v2());
    }

    @GetMapping(value = "/{fileId}/permissions/{permissionId}", produces = "application/json")
    public ResponseEntity<Object> get(@PathVariable("fileId") String fileId,@PathVariable String permissionId){
        MongoInnerDomQuery.MongoInnerDomQueryBuilder builder = MongoInnerDomQuery.builder();
        MongoInnerDomQuery query = builder
                .parentQuery(eq("id",fileId))
                .innerDomQuery(eq("permissions.id", permissionId))
                .innerFieldName("permissions")
                .build();
        Document permissions = driveMongoService
                .queryInnerDocument(query)
                .orElse(null);
        return new ResponseEntity<>(permissions,HttpStatus.OK);
    }

    @GetMapping(value = "/{fileId}/permissions",produces = "application/json")
    public ResponseEntity<Object> list(@PathVariable("fileId") String fileId){
        Object permissions = driveMongoService
                .queryOneFullField(mongoDefaultQuery(fileId))
                .map(document -> document.get("permissions"))
                .orElse(null);
        return new ResponseEntity<>(permissions,HttpStatus.OK);
    }


    @PostMapping(value = "/{fileId}/permissions/{permissionId}", produces = "application/json")
    public ResponseEntity<Object> update(@PathVariable("fileId") String fileId, @PathVariable String permissionId, @RequestBody PermissionUpdateRequestbody bodyData){
        Tuple<Object,HttpStatus> result = driveMongoService
                .queryOneFullField(mongoUnlockQuery(fileId))
                .map(driveFile -> {
                    FileMetaData metaData = BeanUtil.toBean(driveFile,FileMetaData.class);
                    List<FilePermission> permissions = metaData.getPermissions();
                            permissions.forEach(p->{
                                if(p.getId().equals(permissionId))
                                    p.setRole(bodyData.getRole());
                            });
                            BsonArray permissionArray = new BsonArray();
                            permissions.forEach(p -> permissionArray.add(BsonDocument.parse(JsonFormat.toJson(p))));
                            boolean updateResult = driveMongoService.updateOne(mongoDefaultQuery(fileId),set("permissions", permissionArray));
                            return updateResult ? pairs(getSuccessResponse("update execute success!"),HttpStatus.OK) : pairs(getErrorResponse("update execute failed!"),HttpStatus.BAD_REQUEST);
                        }
                ).orElse(pairs(getErrorResponse("file not found or check failed"),HttpStatus.BAD_REQUEST));

        return new ResponseEntity<>(result.v1(),result.v2());
    }
}

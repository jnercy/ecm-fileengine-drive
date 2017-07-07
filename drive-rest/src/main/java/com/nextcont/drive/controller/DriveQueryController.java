package com.nextcont.drive.controller;


import com.nextcont.drive.mongo.MongoQuery;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.utils.IdGenService;
import com.nextcont.drive.utils.Tuple;
import com.nextcont.file.FileList;
import com.nextcont.file.request.file.FileListRequest;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.nextcont.drive.mongo.MongoField.*;
import static com.nextcont.drive.utils.ResponseMaker.getErrorResponse;
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
public class DriveQueryController {

    @Autowired
    private BaseMongoService driveMongoservice;

    @Autowired
    private IdGenService idGenService;


    @GetMapping(value = "/generateIds")
    public Object generateIds() {
        return idGenService.nextId();
    }


    @GetMapping(value = "/{fileId}", produces = "application/json")
    public ResponseEntity<Object> get(@PathVariable("fileId") String fileId) {

        Document result = driveMongoservice
                .queryOne(mongoDefaultQuery(fileId), driveFileExcludeField)
                .orElse(null);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/download/{fileId}", produces = "application/json")
    public ResponseEntity<String> download(@PathVariable("fileId") String fileId) {

        Document result = driveMongoservice
                .queryOne(mongoDefaultQuery(fileId), driveFileExcludeField)
                .orElse(null);

        return new ResponseEntity<>(result.get("webContentLink").toString(), HttpStatus.OK);
    }





    @PostMapping(value = "/list", produces = "application/json")
    public ResponseEntity<Object> list(@RequestBody  FileListRequest fileListRequest) {

        Bson queryBson = mongoListQuery(fileListRequest.getQ());

        MongoQuery query = MongoQuery
                .builder()
                .queryBson(queryBson)
                .pageSize(fileListRequest.getPageSize())
                .pageToken(fileListRequest.getPageToken())
                .orderBy(fileListRequest.getOrderBy())
                .projection(driveFileExcludeField)
                .build();

        Long totalCount = driveMongoservice.count(queryBson);

        List<Document> driveFiles = driveMongoservice.queryFileList(query);

        FileList<Document> result = new FileList<>();

        result.setFiles(driveFiles);

        result.setNextPageToken(totalCount < fileListRequest.getPageSize() * fileListRequest.getPageToken() ? 0 : fileListRequest.getPageToken() + 1);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping(value = "/metadata/{fileId}", produces = "application/json")
    public ResponseEntity<Object> getMetadata(@PathVariable("fileId") String fileId) {
        Tuple<Object, HttpStatus> responseInfo = pairs(getErrorResponse("file not found or check failed"), HttpStatus.BAD_REQUEST);
        driveMongoservice
                .queryOne(mongoDefaultQuery(fileId), excludeUsersRecords)
                .ifPresent(record -> {
                    responseInfo.setV1(record);
                    responseInfo.setV2(HttpStatus.OK);
                });

        return new ResponseEntity<>(responseInfo.v1(), responseInfo.v2());
    }

}

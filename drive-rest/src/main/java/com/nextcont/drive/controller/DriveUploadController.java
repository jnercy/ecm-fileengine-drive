package com.nextcont.drive.controller;

import com.nextcont.drive.jooq.bean.TransitionUnAggregationData;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.service.FileMetadataMaker;
import com.nextcont.drive.utils.HttpClient;
import com.nextcont.drive.utils.IdGenService;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.TikaUtils;
import com.nextcont.file.FileUploadRequest;
import com.nextcont.file.request.transition.TransRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.bson.Document;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nextcont.drive.utils.ResponseMaker.getErrorResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/9
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */

@RestController
@RequestMapping("/drive/v1/files")
@Slf4j
public class DriveUploadController {

    @Autowired
    private BaseMongoService driveMongoService;

    @Autowired
    private IdGenService idGenService;

    @Value("${fdfs.intranetIp}")
    private String dfsIntranetIp;

    @Value("${fdfs.internetIp}")
    private String dfsInternetIp;

    @Value("${fdfs.group}")
    private String groupName;

    @Value("${transition.request}")
    private String transitonRequestUrl;

    @PostMapping(value = "/upload", produces = "application/json")
    public ResponseEntity<Object> uploadFile(@RequestBody  FileUploadRequest request) {
            try {
                TransitionUnAggregationData createFileInfo = TransitionUnAggregationData.builder()
                        .fileId(request.getFileId())
                        .fileName(request.getFileName())
                        .mimeType(TikaUtils.getMimeType(request.getFileName()))
                        .parent(request.getParent())
                        .permissionGenId(idGenService.nextId())
                        .createTime(DateTime.now().toDate())
                        .build();

                Document createFileDom = combineDocument(createFileInfo,request);

                driveMongoService.insert(createFileDom);

                HttpClient.httpPostRequest(transitonRequestUrl, JsonFormat.toJson(TransRequest.getHttpRequest(request.getOriginalFilename(), dfsIntranetIp+"group1/"+request.getUploadFileUrl(), request.getFileId())));
                return new ResponseEntity<>(createFileDom, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(getErrorResponse("You failed to upload " + request.getFileName() + " => " + e.getMessage()), HttpStatus.BAD_REQUEST);
            }
    }


    public Document combineDocument(TransitionUnAggregationData createFileInfo,FileUploadRequest request) {
        Document createFileDom = FileMetadataMaker.makeMetaData(createFileInfo);
        try {
            createFileDom.put("webContentLink", request.getUploadFileUrl());
            createFileDom.put("size",request.getFileSize());
            createFileDom.put("mimeType", TikaUtils.getMimeType(request.getOriginalFilename()));
            createFileDom.put("quotaBytesUsed", request.getFileSize());
            createFileDom.put("fullFileExtension", FilenameUtils.getExtension(request.getOriginalFilename()));
            createFileDom.put("fileExtension", FilenameUtils.getExtension(request.getOriginalFilename()));
            createFileDom.put("md5Checksum", request.getMd5Checksum());
            return createFileDom;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}



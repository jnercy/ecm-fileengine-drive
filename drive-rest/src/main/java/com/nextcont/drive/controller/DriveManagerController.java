package com.nextcont.drive.controller;

import com.nextcont.drive.jooq.bean.TransitionUnAggregationData;
import com.nextcont.drive.service.MongoManagerService;
import com.nextcont.drive.utils.UUIDUtils;
import com.nextcont.file.request.file.FileCreateRequest;
import com.nextcont.file.request.file.FileCreateRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/6
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/files")
@Slf4j
public class DriveManagerController {


    @Autowired
    private MongoManagerService mongoManagerService;




//    @RequestMapping(value = "/folder", method = RequestMethod.POST)
//    public String createFolder(@RequestBody FileCreateRequest request) {
//        TransitionUnAggregationData.TransitionUnAggregationDataBuilder builder = TransitionUnAggregationData.builder();
//        FileCreateRequestBody requestBody = request.getRequestBody();
//        builder
//                .fileId(UUIDUtils.getId())
//                .fileName(requestBody.getName())
//                .mimeType("application/vnd.google-apps.folder")
//                .createTime(DateTime.now().toDate())
//                .webContentLink("");
//        return "";
//    }





}

package com.nextcont.drive.service;

import com.nextcont.drive.jooq.bean.TransitionUnAggregationData;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.file.*;
import com.nextcont.file.folder.FolderCapability;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Updates.*;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/6
 * Time: 16:43
 * To change this template use File | Settings | File Templates.
 */
@Service
@Slf4j
public class MongoManagerService {

    @Autowired
    private BaseMongoService<FileMetaData> fileMetaDataService;

    public void refreshTransitionInfo(TransitionUnAggregationData data){
        Bson updateBson = combine(
                set("iconLink",data.getIconLink()),
                set("mimeType",data.getMimeType()),
                set("webViewLink",data.getPreviewLink()),
                set("thumbnailLink",data.getThumbnailLink()),
                set("hasThumbnail",true));
        boolean updateStatus = fileMetaDataService.updateOne(new Document("id",data.getFileId()),updateBson);
        log.info("refreshTransitionInfo: fileId : {} status : {}",data.getFileId(), updateStatus? "success" : "failed");

    }

}

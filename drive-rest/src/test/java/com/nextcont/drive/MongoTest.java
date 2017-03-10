package com.nextcont.drive;

import com.nextcont.drive.jooq.service.FileCallbackService;
import com.nextcont.drive.mongo.MongoField;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.file.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/6
 * Time: 10:43
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MongoTest {

    @Autowired
    private FileCallbackService defalutFileCallback;

    @Autowired
    private BaseMongoService<FileMetaData> fileMetaDataService;

    @Autowired
    private BaseMongoService<DriveFile> driveFileService;

    @Test
    public void test(){
        defalutFileCallback.queryTransitionFileInfo();
    }

    @Test
    public void testMongo(){
        FileMetaData.FileMetaDataBuilder builder = FileMetaData.builder();
        FileMetaData metaData =
                builder
                        .id("e79963e6-2a7d-4cf5-8eeb-a00c327749ef11")
                        .contentHints(new ContentHints("normal", new Thumbnail("www.baidu.com","image/jpg")))
                        .description("init metata")
                        .mimeType("image/jpg")
                        .modifiedTime("")
                        .folderColorRgb("normal")
                        .build();
//        fileMetaDataService.insert(metaData);
    }

    @Test
    public void testCurd(){
        String filedId = "7d4a3ef8-015f-431e-95c2-16b505bbb8da";
        List<FileMetaData> result =
                fileMetaDataService.query(and(eq("id",filedId),eq("userId","")), MongoField.driveFileExcludeField);
        System.out.println();
    }

    @Test
    public void testDriveFile(){
        String filedId = "7d4a3ef8-015f-431e-95c2-16b505bbb8da";
        List<DriveFile> result =
                driveFileService.query(eq("id",filedId), MongoField.driveFileExcludeField);
        System.out.println();
    }



}

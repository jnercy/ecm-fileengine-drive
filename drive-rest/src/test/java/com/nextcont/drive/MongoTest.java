package com.nextcont.drive;

import com.nextcont.drive.jooq.service.FileCallbackService;
import com.nextcont.drive.mongo.MongoField;
import com.nextcont.drive.mongo.MongoInnerDomQuery;
import com.nextcont.drive.mongo.MongoQuery;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.file.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.nextcont.drive.mongo.MongoField.driveFileExcludeField;

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
    private BaseMongoService<FilePermission> permissionService;

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
        MongoInnerDomQuery.MongoInnerDomQueryBuilder builder = MongoInnerDomQuery.builder();
        MongoInnerDomQuery query = builder
                .parentQuery(eq("id","7d4a3ef8-015f-431e-95c2-16b505bbb8da"))
                .innerDomQuery(eq("permissions.type", "user"))
                .innerFieldName("permissions")
                .build();
        FilePermission permissions = permissionService
                .queryInnerDocument(query)
                .orElse(null);

        System.out.println(permissions);
    }


    @Test
    public void testDriveFile(){
        String filedId = "7d4a3ef8-015f-431e-95c2-16b505bbb8da";
        List<DriveFile> result =
                driveFileService.query(eq("id",filedId), MongoField.driveFileExcludeField);
        System.out.println();
    }

    @Test
    public void testList(){
        MongoQuery query = MongoQuery
                .builder()
                .queryBson(new Document("id","844474978685419520"))
                .pageSize(2)
                .pageToken(1)
                .projection(driveFileExcludeField)
                .build();

        List<DriveFile> driveFiles = driveFileService.queryFileList(query);
        System.out.println();
    }

}

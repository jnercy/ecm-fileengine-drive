package com.nextcont.drive.mongo.service;

import com.mongodb.Block;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.file.DriveFile;
import com.nextcont.file.request.FileListRequest;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nextcont.drive.mongo.MongoField.driveFileExcludeField;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/10
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DriveFileService extends BaseMongoService<DriveFile> {

    @Value("${mongodb.collection.metadata}")
    private String collection4metadata;

    @PostConstruct
    public void init() {
        database = pool.getClient().getDatabase(databaseName);
        mongoCollection = database.getCollection(collection4metadata);
    }


    @Override
    public List<DriveFile> queryFileList(FileListRequest query) {
        List<DriveFile> result = new ArrayList<>();
        mongoCollection
                .find(new Document("userRecords.userId",query.getUserId()))
                .projection(driveFileExcludeField)
                .skip((query.getPageToken() - 1) * query.getPageSize())
                .limit(query.getPageSize())
                .forEach((Block<Document>) document ->
                        Optional
                                .ofNullable(JsonFormat.convert2Object(document.toJson(),getEntityClass()))
                                .ifPresent(doc->result.add(doc.get())));

        return result;
    }
}

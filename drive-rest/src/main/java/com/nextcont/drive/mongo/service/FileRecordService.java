package com.nextcont.drive.mongo.service;

import com.nextcont.file.FileProcessRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/10
 * Time: 13:19
 * To change this template use File | Settings | File Templates.
 */

@Service
public class FileRecordService extends BaseMongoService<FileProcessRecord> {

    @Value("${mongodb.collection.record}")
    private String collection4metadata;

    @PostConstruct
    public void init(){
        database = pool.getClient().getDatabase(databaseName);
        mongoCollection = database.getCollection(collection4metadata);
    }



}

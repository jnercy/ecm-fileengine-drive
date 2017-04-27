package com.nextcont.drive.mongo.service;

import com.nextcont.file.FileRevision;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/24
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FileRevisionService extends  BaseMongoService<FileRevision> {

    @Value("${mongodb.collection.metadata}")
    private String collection4metadata;

    @PostConstruct
    public void init() {
        database = pool.getClient().getDatabase(databaseName);
        mongoCollection = database.getCollection(collection4metadata);
    }
}

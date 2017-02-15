package com.nextcont.drive.mongo.service;

import com.nextcont.drive.mongo.MongoQuery;
import com.nextcont.file.DriveFile;
import com.nextcont.file.FileList;
import com.nextcont.file.request.FileListRequest;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/10
 * Time: 11:45
 * To change this template use File | Settings | File Templates.
 */
public interface BaseMongoDAO<T>{

    void insert(T t);

    void insertMany(List<T> t);

    List<T> query(Bson query,Bson excludeField);

    List<T> queryFullField(Bson query);

    List<DriveFile> queryFileList(FileListRequest query);

    Optional<T> queryOne(Bson query, Bson excludeField);

    Optional<T> queryOneFullField(Bson query);

    boolean updateOne(Bson query, Bson updates);

    boolean updateMany(Bson query, Bson updates);
}
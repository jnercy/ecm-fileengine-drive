package com.nextcont.drive.mongo.service;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.nextcont.drive.mongo.MongoClientPool;
import com.nextcont.drive.mongo.MongoQuery;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.ReflectionUtils;
import com.nextcont.file.DriveFile;
import com.nextcont.file.FileList;
import com.nextcont.file.request.FileListRequest;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Projections.excludeId;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/9
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseMongoService<T> implements BaseMongoDAO<T>{

    @Autowired
    MongoClientPool pool;

    @Value("${mongodb.database}")
    public String databaseName;

    MongoDatabase database;

    MongoCollection<Document> mongoCollection;

    @Override
    public void insert(T t){
        mongoCollection.insertOne(Document.parse(JsonFormat.convertJson(t).get()));
    }

    @Override
    public void insertMany(List<T> t){

        List<Document> insertData = t
                .stream()
                .map(data->Document.parse(JsonFormat.convertJson(data).get()))
                .collect(Collectors.toList());
        mongoCollection.insertMany(insertData);
    }

    @Override
    public Optional<T> queryOne(Bson query, Bson excludeField) {

        Document document = mongoCollection
                .find(query)
                .projection(excludeField)
                .first();

        return document==null ? Optional.empty() : JsonFormat.convert2Object(document.toJson(),getEntityClass());
    }

    @Override
    public Optional<T> queryOneFullField(Bson query) {
        Document document = mongoCollection
                .find(query)
                .first();

        return document==null ? Optional.empty() : JsonFormat.convert2Object(document.toJson(),getEntityClass());
    }

    @Override
    public List<T> query(Bson query,Bson excludeField){
        List<T> result = new ArrayList<>();

        mongoCollection
                .find(query)
                .projection(excludeField)
                .forEach((Block<Document>) document ->
                        Optional
                                .ofNullable(JsonFormat.convert2Object(document.toJson(),getEntityClass()))
                                .ifPresent(doc->result.add(doc.get())));
        return result;
    }

    @Override
    public List<T> queryFullField(Bson query) {
        List<T> result = new ArrayList<>();

        mongoCollection
                .find(query)
                .forEach((Block<Document>) document ->
                        Optional
                                .ofNullable(JsonFormat.convert2Object(document.toJson(),getEntityClass()))
                                .ifPresent(doc->result.add(doc.get())));
        return result;
    }

    @Override
    public List<DriveFile> queryFileList(FileListRequest query) {
        return null;
    }


    @Override
    public boolean updateOne(Bson query, Bson updates) {
        UpdateResult result = mongoCollection.updateOne(query,updates);
        return result.getModifiedCount()>0;
    }

    @Override
    public boolean updateMany(Bson query, Bson updates) {
        UpdateResult result = mongoCollection.updateMany(query,updates);
        return result.getModifiedCount()>0;
    }


    public Class<T> getEntityClass(){
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }
}

package com.nextcont.drive.mongo.service;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.nextcont.drive.mongo.MongoClientPool;
import com.nextcont.drive.mongo.MongoInnerDomQuery;
import com.nextcont.drive.mongo.MongoQuery;
import com.nextcont.drive.utils.BeanUtil;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.ReflectionUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.*;


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
    public void insert(Document doc){
        mongoCollection.insertOne(doc);
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
        return document!=null ? Optional.of(BeanUtil.toBean(document,getEntityClass())): Optional.empty();
    }

    @Override
    public Optional<T> queryOneFullField(Bson query) {
        Document document = mongoCollection
                .find(query)
                .first();
        return document!=null ? Optional.of(BeanUtil.toBean(document,getEntityClass())): Optional.empty();
    }

    @Override
    public List<T> query(Bson query,Bson excludeField){
        List<T> result = new ArrayList<>();

        mongoCollection
                .find(query)
                .projection(excludeField)
                .forEach((Block<Document>) document -> result.add(BeanUtil.toBean(document,getEntityClass())));

        return result;
    }

    @Override
    public List<T> queryFullField(Bson query) {
        List<T> result = new ArrayList<>();

        mongoCollection
                .find(query)
                .forEach((Block<Document>) document -> result.add(BeanUtil.toBean(document,getEntityClass())));
        return result;
    }

    @Override
    public List<T> queryFileList(MongoQuery query) {
        List<T> result = new ArrayList<>();
        mongoCollection
                .find(query.getQueryBson())
                .projection(query.getProjection())
                .skip((query.getPageToken() - 1) * query.getPageSize())
                .limit(query.getPageSize())
                .forEach((Block<Document>) document -> result.add(BeanUtil.toBean(document,getEntityClass())));
        return result;
    }

    @Override
    public Long count(Bson query) {
        return mongoCollection.count(query);
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

    @Override
    public boolean delete(Bson query) {
        DeleteResult result = mongoCollection.deleteMany(query);
        return result.getDeletedCount()>0;
    }

    @Override
    public Optional<T> queryInnerDocument(MongoInnerDomQuery query) {
        Document result= mongoCollection
                .aggregate(
                        Arrays.asList(
                                match(query.getParentQuery()),
                                project(fields(include(query.getInnerFieldName()),excludeId())),
                                unwind("$"+query.getInnerFieldName()),
                                match(query.getInnerDomQuery())
                        )
                )
                .first();

        Document innerDoc = result.get( query.getInnerFieldName(),Document.class);

        return JsonFormat.convert2Object(innerDoc.toJson(),getEntityClass());
    }


    public Class<T> getEntityClass(){
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }
}

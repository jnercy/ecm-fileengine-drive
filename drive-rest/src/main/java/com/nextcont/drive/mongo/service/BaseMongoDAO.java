package com.nextcont.drive.mongo.service;

import com.nextcont.drive.mongo.MongoInnerDomQuery;
import com.nextcont.drive.mongo.MongoQuery;
import org.bson.Document;
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
public interface BaseMongoDAO{

    void insert(Document doc);

    void insertMany(List<Document> t);

    List<Document> query(Bson query,Bson excludeField);

    List<Document> queryFullField(Bson query);

    List<Document> queryFileList(MongoQuery query);

    Optional<Document> queryOne(Bson query, Bson excludeField);

    Optional<Document> queryOneFullField(Bson query);

    boolean updateOne(Bson query, Bson updates);

    boolean updateMany(Bson query, Bson updates);

    boolean delete(Bson query);

    Long count(Bson query);

    Optional<Document> queryInnerDocument(MongoInnerDomQuery query);
}

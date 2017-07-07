package com.nextcont.drive.mongo.service;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.nextcont.drive.aspect.AuthAspect;
import com.nextcont.drive.mongo.MongoClientPool;
import com.nextcont.drive.mongo.MongoInnerDomQuery;
import com.nextcont.drive.mongo.MongoQuery;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.file.UserRecord;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.*;
import static com.nextcont.drive.mongo.MongoField.buildSortBson;
import static com.nextcont.drive.mongo.MongoField.defaultSortBson;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/9
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseMongoService implements BaseMongoDAO{

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
    public void insertMany(List<Document> t){
        mongoCollection.insertMany(t);
    }

    @Override
    public Optional<Document> queryOne(Bson query, Bson excludeField) {

        Document document = mongoCollection
                .find(query)
                .projection(excludeField)
                .first();

        return  document ==null ? Optional.empty() : Optional.of(assemblyDocument(document));
    }

    @Override
    public Optional<Document> queryOneFullField(Bson query) {

        Document document = mongoCollection
                .find(query)
                .first();

        return  document ==null ? Optional.empty() : Optional.of(assemblyDocument(document));

    }

    @Override
    public List<Document> query(Bson query,Bson excludeField){
        List<Document> result = new ArrayList<>();

        mongoCollection
                .find(query)
                .projection(excludeField)
                .forEach((Block<Document>) document-> result.add(assemblyDocument(document)));

        return result;
    }

    @Override
    public List<Document> queryFullField(Bson query) {
        List<Document> result = new ArrayList<>();

        mongoCollection
                .find(query)
                .forEach((Block<Document>) document-> result.add(assemblyDocument(document)));

        return result;
    }

    @Override
    public List<Document> queryFileList(MongoQuery query) {
        List<Document> result = new ArrayList<>();
        try {
            mongoCollection
                    .find(query.getQueryBson())
                    .sort(buildSortBson(query.getOrderBy()))
                    .projection(query.getProjection())
                    .skip((query.getPageToken() - 1) * query.getPageSize())
                    .limit(query.getPageSize())
                    .forEach((Block<Document>) document -> result.add(assemblyDocument(document)));
        }catch (Exception e){
            e.printStackTrace();
        }
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
    public Optional<Document> queryInnerDocument(MongoInnerDomQuery query) {
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

        return Optional.of(innerDoc);
    }


    @SuppressWarnings("unchecked")
    public Document assemblyDocument(Document document){
        List<Document> list = (ArrayList<Document>)document.get("userRecords");
        list.forEach(userRecord->{
            if(userRecord.get("rootId").equals(AuthAspect.getAuthTokenInfo().getRootid())){
                document.put("parents",userRecord.get("parents"));
                document.put("rootId",userRecord.get("rootId"));
                document.put("viewedByMeTime",userRecord.get("viewedByMeTime"));
                document.put("viewedByMe",userRecord.get("viewedByMe"));
                document.put("modifyByMe",userRecord.get("modifyByMe"));
                document.put("modifyByMeTime",userRecord.get("modifyByMeTime"));
                document.put("sharedWithMeTime",userRecord.get("sharedWithMeTime"));
                document.put("ownedByMe",userRecord.get("ownedByMe"));
            }
        });
        return document;
    }

}

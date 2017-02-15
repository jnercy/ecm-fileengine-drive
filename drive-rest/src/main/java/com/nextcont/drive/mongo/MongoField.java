package com.nextcont.drive.mongo;

import org.bson.conversions.Bson;

import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/10
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 */
public class MongoField {

    public static Bson driveFileExcludeField = fields(exclude("description","properties","appProperties","imageMediaMetadata","videoMediaMetadata","userRecords"),excludeId());

    public static Bson excludeUsersRecords = exclude("userRecords");
}

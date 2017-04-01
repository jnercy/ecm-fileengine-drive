package com.nextcont.drive.mongo;

import lombok.Builder;
import lombok.Getter;
import org.bson.conversions.Bson;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/14
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
public class MongoInnerDomQuery {

    private Bson parentQuery;

    private Bson innerDomQuery;

    private String innerFieldName;
}

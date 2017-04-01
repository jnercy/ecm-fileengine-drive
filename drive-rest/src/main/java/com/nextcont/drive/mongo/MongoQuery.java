package com.nextcont.drive.mongo;

import lombok.Builder;
import lombok.Getter;
import org.bson.conversions.Bson;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/15
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
public class MongoQuery {

    private Integer pageSize;

    private Integer pageToken;

    private String orderBy;

    private Bson queryBson;

    private Bson projection;




}

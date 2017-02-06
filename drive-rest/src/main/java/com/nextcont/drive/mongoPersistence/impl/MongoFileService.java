package com.nextcont.drive.mongoPersistence.impl;

import bean.FileAggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/12
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
public interface MongoFileService extends MongoRepository<FileAggregation,String> {
}

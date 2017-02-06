package com.nextcont.drive.mongoPersistence.impl;

import bean.FileProcessRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/12
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
public interface MongoFileRecordService extends MongoRepository<FileProcessRecord,String> {

}

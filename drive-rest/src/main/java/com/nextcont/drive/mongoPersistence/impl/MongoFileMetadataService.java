package com.nextcont.drive.mongoPersistence.impl;

import bean.FileMetaData;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/3
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public interface MongoFileMetadataService extends MongoRepository<FileMetaData,String> {



}

package com.nextcont.drive.mongo;

import com.mongodb.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/9
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
@Component
@Slf4j
@Getter
public class MongoClientPool {

    private MongoClient client;

    @Value("${mongodb.host}")
    private String host;

    @Value("${mongodb.port}")
    private Integer port;

    @Value("${mongodb.url}")
    private String  url;

    @PostConstruct
    public void initClient(){
        client = new MongoClient(new MongoClientURI(url));
    }
}

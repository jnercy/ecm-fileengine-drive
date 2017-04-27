package com.nextcont.drive.utils;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/7
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public class UUIDUtils {

    public static String getId(){
        return UUID.randomUUID().toString();
    }
}

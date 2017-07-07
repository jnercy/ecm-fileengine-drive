package com.nextcont.drive.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by wangxudong on 2017/6/2.
 */
public class DateTimeUtil {


    public static DateTime conver2DateTime(String time){
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return DateTime.parse(time, format);
    }

    public static String nowTime2String(){
        return new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }
}

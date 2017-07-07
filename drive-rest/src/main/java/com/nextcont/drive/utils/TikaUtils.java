package com.nextcont.drive.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.io.TikaInputStream;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/5/24
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class TikaUtils {


    public static String getMimeType(String filePath) {
        // 利用Tika的detect方法检测文件的实际类型
        return new Tika().detect(filePath);
    }




    public static String getMimeType(File file) {
        // 利用Tika的detect方法检测文件的实际类型
        TikaInputStream str;
        String mediaType = "unknown";

        try {
            str = TikaInputStream.get(file.toPath());
            mediaType = new Tika().detect(str, file.getName());
        } catch (IOException e) {
            log.error("error occurs when get file mime type");
            e.printStackTrace();
        }

        return mediaType;
    }


}

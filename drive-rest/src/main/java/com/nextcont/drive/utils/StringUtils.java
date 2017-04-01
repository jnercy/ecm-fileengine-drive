package com.nextcont.drive.utils;

import java.util.Optional;

/**
 * String帮助类
 * Created by wangxudong on 17/2/20.
 */
public class StringUtils {

    public static boolean isNotEmpty(String str){
        return str!=null && !"".equals(str.trim());
    }

    public static boolean isEmpty(String str){
        return str==null || "".equals(str.trim());
    }

    public static Optional<String> optionalOf(String str){
        return Optional.ofNullable(str).map(string -> "".equals(string.trim()) ? null : string);
    }

}

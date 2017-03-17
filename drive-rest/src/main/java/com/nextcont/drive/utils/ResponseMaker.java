package com.nextcont.drive.utils;

import com.nextcont.file.response.ExecutionRecord;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/13
 * Time: 17:43
 * To change this template use File | Settings | File Templates.
 */
public class ResponseMaker {

    public static String getSuccessResponse(String message) {
        return JsonFormat.convertJson(ExecutionRecord.generateSuccessResponse(message)).orElse(message);
    }

    public static String getErrorResponse(String message) {
        return JsonFormat.convertJson(ExecutionRecord.generateErrorResponse(message)).orElse(message);
    }
}

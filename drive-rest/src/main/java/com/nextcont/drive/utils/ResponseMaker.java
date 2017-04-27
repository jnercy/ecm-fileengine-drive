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

    public static Object getSuccessResponse(String message) {
        return ExecutionRecord.generateSuccessResponse(message);
    }

    public static Object getErrorResponse(String message) {
        return ExecutionRecord.generateErrorResponse(message);
    }
}

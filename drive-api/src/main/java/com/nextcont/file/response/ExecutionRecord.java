package com.nextcont.file.response;


import com.nextcont.file.ExecutionStatus;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/16
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */

@Data
public class ExecutionRecord {

    private String domain = "global";

    private String message;

    private String code;



    public static ExecutionRecord generateErrorResponse(String message){
        return new ExecutionRecord(message,ExecutionStatus.error.name());
    }


    public static ExecutionRecord generateSuccessResponse(String message){
        return new ExecutionRecord(message,ExecutionStatus.success.name());
    }


    public ExecutionRecord(String message, String code) {
        this.message = message;
        this.code = code;
    }
}

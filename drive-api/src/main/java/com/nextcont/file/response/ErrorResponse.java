package com.nextcont.file.response;

import com.nextcont.file.ErrorInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/5/10
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 */
@Data
public class ErrorResponse implements Serializable{

    private List<ErrorInfo> errors;

    private Integer code;

    private String message;



    public static ErrorResponse createErrorResponse(Integer errorCode,String message){
        ErrorResponse res = new ErrorResponse();

        ErrorInfo.ErrorInfoBuilder builder = ErrorInfo.builder();
        builder.domain("global")
                .location("file")
                .locationType("other")
                .message(message)
                .reason(message);
        res.setErrors(Arrays.asList(builder.build()));
        res.setCode(errorCode);
        res.setMessage(message);
        return res;
    }
}

package com.nextcont.file.response;


import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/16
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */

@Data
public class Error {


    private String domin = "global";

    private String message;

    private Integer code;



    public static Error generateErrorResponse(String message,Integer code){
        Error response = new Error();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

}

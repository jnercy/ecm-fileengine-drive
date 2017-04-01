package com.nextcont.file.request.transition;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/21
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
@Data
public class TransRequest {

    private String clientId  = "ws-client";

    private String clientSecret = "cFs8nBLMrVGoRwR7DhWzGQ";

    private String callackUrl;

    private RequestData data;

    public static TransRequest getHttpRequest(String fileName,String source,String fileId){
        TransRequest request = new TransRequest();
        RequestData data = new RequestData();
        data.setFileName(fileName);
        data.setSource(source);
        data.setFileId(fileId);
        request.setData(data);
        return request;
    }
}

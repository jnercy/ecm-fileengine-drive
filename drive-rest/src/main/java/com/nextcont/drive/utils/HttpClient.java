package com.nextcont.drive.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/21
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class HttpClient {

    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient okHttpClient = new OkHttpClient();


    public static void httpPostRequest(String url,String json){

        //step 1: 创建一个requestBody对象
        RequestBody requestBody = RequestBody.create(JSON,json);

        //step 3: 创建请求
        Request request = new Request.Builder().url(url)
                .post(requestBody)
                .build();

        //step 4： 建立联系 创建Call对象
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 17-1-4  请求失败
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 17-1-4 请求成功
                log.info(response.body().string());
            }
        });
    }

}

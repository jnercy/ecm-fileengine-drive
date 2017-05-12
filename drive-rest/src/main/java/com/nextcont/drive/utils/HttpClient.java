package com.nextcont.drive.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

import static com.nextcont.drive.utils.TupleFactories.pairs;

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

        RequestBody requestBody = RequestBody.create(JSON,json);

        Request request = new Request.Builder().url(url)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.info(response.body().string());
            }
        });
    }

    public static Tuple<Integer,String> httpGetRequest(String url, Map<String,String> parames){
        Request.Builder builder = new Request.Builder().url(url);
        parames.forEach(builder::addHeader);

        Request request = builder.build();
        try{
            Response response = okHttpClient.newCall(request).execute();
            return pairs(response.code(),response.body().string());
        }catch (IOException e){
            return pairs(500,e.getMessage());
        }
    }

}

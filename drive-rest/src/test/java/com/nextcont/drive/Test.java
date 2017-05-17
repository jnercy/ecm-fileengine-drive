package com.nextcont.drive;

import com.nextcont.drive.utils.HttpClient;
import com.nextcont.drive.utils.Tuple;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/24
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class Test {

    private final static String domainGenerateIdUrl = "http://139.196.136.113/drive/v1/files/generateIds";

    private final static String domainCreateFileUrl = "http://139.196.136.113/drive/v1/files/create";

    private final static String domainUploadFileUrl = "http://139.196.136.113/upload/drive/v1/files";

    private final static String mimeType = "application/octet-stream";


    public static void main(String[] args) {
        File file = new File("D:\\drive-test-zip");
        if (file.exists()) {
             Arrays.stream(file.listFiles()).forEach(uploadFile->{
                 String generateId = HttpClient.httpGetRequest(domainGenerateIdUrl,new HashMap<>()).v2();

                 log.debug(generateId);

                 String createFileJson = "{\n" +
                         "\t\"mimeType\":\"\",\n" +
                         "\t\"id\":\"{id}\",\n" +
                         "\t\"name\" : \"{name}\",\n" +
                         "\t\"parents\" : [\"0APymvC2SzZuDUk9PVA\"]\n" +
                         "}";
                 Tuple<Integer,String> result = HttpClient.httpPostRequest(domainCreateFileUrl,createFileJson.replace("{id}",generateId).replace("{name}",uploadFile.getName()));
                 log.debug(result.toString());

                 if(result.v1()==200) {
                     try {
                         MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                         builder.addFormDataPart("file", uploadFile.getName(), RequestBody.create(MediaType.parse(mimeType), uploadFile));
                         builder.addFormDataPart("uploadType", "media");
                         builder.addFormDataPart("fileId", generateId);
                         builder.addFormDataPart("path", "");

                         RequestBody requestBody = builder.build();

                         Request request = new Request.Builder()
                                 .url(domainUploadFileUrl)
                                 .post(requestBody)
                                 .build();

                         OkHttpClient client = new OkHttpClient.Builder()
                                 .connectTimeout(30, TimeUnit.SECONDS)
                                 .readTimeout(30, TimeUnit.SECONDS)
                                 .writeTimeout(30, TimeUnit.SECONDS)
                                 .build();

                         client.newCall(request).enqueue(new okhttp3.Callback(){
                               @Override
                               public void onFailure(Call call, IOException e) {
                                   e.printStackTrace();
                               }

                               @Override
                               public void onResponse(Call call, okhttp3.Response response) throws IOException {
                                   log.debug(response.body().string());
                               }
                           });

                     }catch (Exception e){
                         e.printStackTrace();
                     }
                 }
                 else
                     log.error(result.v2());
             });

        }
    }
}

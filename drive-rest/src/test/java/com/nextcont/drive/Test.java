package com.nextcont.drive;

import com.nextcont.drive.utils.HttpClient;
import com.nextcont.drive.utils.Tuple;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


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

    private final static String domainUploadFileUrl = "http://139.196.136.113/drive/v1/files/upload";

    private final static String mimeType = "application/octet-stream";


    public static void main(String[] args) throws InterruptedException {
//        File file = new File("C:\\Users\\junxinpc005\\Pictures");
        File file = new File("/Users/wangxudong/Downloads");
        if (file.exists()) {
            List<File> uploadFiles = Arrays.stream(file.listFiles())
                    .filter(tempFile->!tempFile.isDirectory())
                    .collect(Collectors.toList());


            AtomicInteger atomicInteger = new AtomicInteger(uploadFiles.size());
            ExecutorService executorService = Executors.newFixedThreadPool(8);

            CountDownLatch cdl = new CountDownLatch(atomicInteger.get());
            uploadFiles.forEach(uploadFile -> {

                Runnable task = () -> {
                    String generateId = HttpClient.httpGetRequest(domainGenerateIdUrl, new HashMap<>()).v2();
                    try {
                        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                        builder.addFormDataPart("file", uploadFile.getName(), RequestBody.create(MediaType.parse(mimeType), uploadFile));
                        builder.addFormDataPart("fileId", generateId);
                        builder.addFormDataPart("parent", "0APymvC2SzZuDUk9PVA");

                        RequestBody requestBody = builder.build();

                        Request request = new Request.Builder()
                                .url(domainUploadFileUrl)
                                .post(requestBody)
                                .build();

                        OkHttpClient client = new OkHttpClient.Builder()
                                .connectTimeout(300, TimeUnit.SECONDS)
                                .readTimeout(300, TimeUnit.SECONDS)
                                .writeTimeout(300, TimeUnit.SECONDS)
                                .retryOnConnectionFailure(true)
                                .build();

                        Response response = client.newCall(request).execute();
                        log.debug(response.body().string());
                        cdl.countDown();
                        log.info("file upload success , unfinished task count=>{}",atomicInteger.addAndGet(-1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };

                executorService.execute(task);
            });

            cdl.await();
            System.out.println("test over");
        }
    }
}

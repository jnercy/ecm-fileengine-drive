package com.nextcont.drive;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.nextcont.drive.utils.HttpClient;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.file.request.transition.TransRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/21
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
@Import(FdfsClientConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HttpTest {

    @Test
    public void testhttp() throws InterruptedException {
        String picUrl = "http://139.196.138.51/group1/M00/00/00/wKgBEFjPtRmAOEMpABcES0_vR-g112.gif";
        String json = JsonFormat.toJson(TransRequest.getHttpRequest("dog.gif",picUrl,"7d4a3ef8-015f-431e-95c2-16b505bbb8da"));
        HttpClient.httpPostRequest("http://139.196.136.113:8080/rest/trans/request",json);

        CountDownLatch cdl = new CountDownLatch(1);

        cdl.await();

    }



}

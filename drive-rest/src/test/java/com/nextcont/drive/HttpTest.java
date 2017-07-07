package com.nextcont.drive;

import com.nextcont.drive.utils.HttpClient;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.Tuple;
import com.nextcont.file.UserGroup;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/21
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */

public class HttpTest {

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis()/1000;
        System.out.println(timestamp);
        String md5 = DigestUtils.md5Hex("yiguo.chen@nextcont.comnextcont"+timestamp+"nq4aK?[b#brC6(KC");
        String picUrl = "https://nextcont-api.inecm.cn/v1/group?suffix=nextcont&email=yiguo.chen@nextcont.com&t="+timestamp+"&s="+md5;
        Tuple<Integer,String> result = HttpClient.httpGetRequest(picUrl,null);
        UserGroup ug = JsonFormat.convert2Object(result.v2(), UserGroup.class).get();
        System.out.println();
    }



}

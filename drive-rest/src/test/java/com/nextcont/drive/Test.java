package com.nextcont.drive;

import com.nextcont.drive.utils.HttpClient;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.Tuple;
import com.nextcont.file.TokenInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/24
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    private static final String host = "api.inecm.cn";
    // private static final String host = "api.nextcont.com";
    private static final String testUserName = "yiguo.chen@nextcont.com";
    private static final String testPassword = "Passw0rD";

    private static String ncat = "";

    public static void main(String[] args) {
//        Map<String,String> headerMap = new HashMap<>();
//        headerMap.put("authorization","OAuth "+"gAVmD2R8CeQEf89iRD6p9fIKbSckGNww");
//        Tuple<Integer,String> result = HttpClient.httpGetRequest("https://" + host + "/o/state",headerMap);
//
//        if(result.v1()==200){
//            TokenInfo tokenInfo = JsonFormat.convert2Object(result.v2(),new TokenInfo()).get();
//            System.out.println(result.v2());
//            System.out.println(tokenInfo);
//        }else{
//            System.out.println("error");
//        }

        String q = "trashed=\"true\"";
        if(q.contains("trashed"))
            System.out.println(q.trim().replace("trashed=",""));
    }
}

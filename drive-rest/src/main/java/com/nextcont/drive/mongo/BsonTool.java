package com.nextcont.drive.mongo;

import org.bson.*;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/13
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 */
public class BsonTool {


    /**
     * Java对象转BsonValue对象
     * @param obj
     * @return
     */
    public static BsonValue objectToBsonValue(Object obj){

        if (obj instanceof Integer){
            return new BsonInt32((Integer) obj);
        }

        if (obj instanceof String){
            return new BsonString((String) obj);
        }

        if (obj instanceof Long){
            return new BsonInt64((Long) obj);
        }

        if (obj instanceof Date){
            return new BsonDateTime(((Date) obj).getTime());
        }
        return new BsonNull();
    }


//    public static BsonArray parseArray(List data){
//        JSONArray userData = JSONArray.parseArray(userDataObj);
//        BsonArray bsonArray = new BsonArray();
//        JSONObject jo;
//        for (int i = 0; i < userData.size(); i++) {
//            jo = userData.getJSONObject(i);
//            BsonDocument document = new BsonDocument();
//
//            if (!jo.isEmpty()) {
//                Set<String> set = jo.keySet();
//                for (String key : set) {
//                    document.put(key, BsonTool.objectToBsonValue(jo.get(key)));
//                }
//            }
//            bsonArray.add(document);
//        }
//    }

}

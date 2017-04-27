package com.nextcont.drive.utils;

import java.util.Map;

/**
 * 元组的工厂方法
 * Created by zhangqinghua on 16/2/23.
 */
public class TupleFactories {

    public static <V1,V2> Tuple<V1,V2> pairs(V1 v1, V2 v2 ){
        return new Tuple<V1,V2>(v1,v2);
    }

    public static <V1,V2> Tuple<V1,V2> pairs(Map.Entry<V1,V2> entry){
        return new Tuple<V1,V2>(entry.getKey(),entry.getValue());
    }

    public static <V1,V2,V3> Tuple1<V1,V2,V3>  triple(V1 v1,V2 v2,V3 v3){
        return  new Tuple1<V1, V2, V3>(v1,v2,v3);
    }

}

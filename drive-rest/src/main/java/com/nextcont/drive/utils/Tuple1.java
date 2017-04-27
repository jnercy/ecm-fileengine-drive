package com.nextcont.drive.utils;

/**
 * Created by wangxudong on 2016-04-12
 */
public class Tuple1<V1,V2,V3>  extends Tuple<V1,V2> {

    private V3 v3;

    public Tuple1(V1 v1, V2 v2 , V3 v3) {
        super(v1, v2);
        this.v3 = v3;
    }

    public V3 v3(){
        return v3;
    }

    @Override
    public String toString() {
        return "Tuple("+ v1 + "," + v2 + "," +v3 +")";
    }
}

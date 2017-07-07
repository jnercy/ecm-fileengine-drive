package com.nextcont.drive.utils;

import lombok.Setter;

import java.io.Serializable;

/**
 * 元组
 * Created by wangxudong on 16/2/23.
 */
@Setter
public class Tuple<V1,V2> implements Serializable{

    protected V1 v1;
    protected V2 v2;

    public Tuple(V1 v1, V2 v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    public V1 v1(){
        return v1;
    }

    public V2 v2(){
        return v2;
    }

    @Override
    public String toString() {
        return "Tuple("+ v1 + "," + v2 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;

        if (v1 != null ? !v1.equals(tuple.v1) : tuple.v1 != null) return false;
        return v2 != null ? v2.equals(tuple.v2) : tuple.v2 == null;

    }

    @Override
    public int hashCode() {
        int result = v1 != null ? v1.hashCode() : 0;
        result = 31 * result + (v2 != null ? v2.hashCode() : 0);
        return result;
    }
}

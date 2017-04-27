package com.nextcont.drive.utils;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/23
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    public String name();

    public String text() default "这是一个属性映射";
}
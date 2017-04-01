package com.nextcont.drive;

import com.nextcont.drive.utils.BeanUtil;
import org.bson.Document;


/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/24
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        Document doc = BeanUtil.toBson("drive");
        System.out.println();
    }
}

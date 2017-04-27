package com.nextcont.file;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/16
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public enum ExecutionStatus {

    notFound(404),error(500),success(200);

    ExecutionStatus(int i) {

    }
}

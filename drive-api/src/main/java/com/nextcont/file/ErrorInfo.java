package com.nextcont.file;

import lombok.Builder;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/5/10
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
@Builder
public class ErrorInfo {

    private String domain;

    private String reason;

    private String message;

    private String locationType;

    private String location;
}

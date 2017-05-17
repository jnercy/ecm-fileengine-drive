package com.nextcont.file;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/5/10
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Data
public class ErrorInfo implements Serializable{

    private String domain;

    private String reason;

    private String message;

    private String locationType;

    private String location;
}

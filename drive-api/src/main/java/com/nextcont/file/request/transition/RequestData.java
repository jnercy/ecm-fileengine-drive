package com.nextcont.file.request.transition;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/21
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
@Data
public class RequestData {

    private String fileName;

    private String fileId;

    private String mimeType;

    private Long length;

    private String source;

    private String ftpUserName;

    private String ftpPassword;

    private String uploadType = "http";

    private String converter = "local";

}

package com.nextcont.file.request.file;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 14:34
 * To change this template use File | Settings | File Templates.
 */
@Data
public class FileCopyRequest {

    private boolean ignoreDefaultVisibility;

    private boolean keepRevisionForever;

    private boolean supportsTeamDrives;

    private String ocrLanguage;
}

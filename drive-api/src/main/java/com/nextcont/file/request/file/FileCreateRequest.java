package com.nextcont.file.request.file;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/6
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
@Data
public class FileCreateRequest {

    private boolean ignoreDefaultVisibility;

    private boolean keepRevisionForever;

    private String ocrLanguage;

    private boolean useContentAsIndexableText;

    private boolean supportsTeamDrives;

}

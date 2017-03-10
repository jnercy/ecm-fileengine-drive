package com.nextcont.file.request.file;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/10
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
@Data
public class FileMetadataRequest {

    private String addParents;

    private boolean keepRevisionForever;

    private String ocrLanguage;

    private String removeParents;

    private boolean supportsTeamDrives;

    private boolean useContentAsIndexableText;
}

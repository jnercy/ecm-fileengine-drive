package com.nextcont.file.request.file;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/17
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
@Data
public class FileListRequest{

    private String corpora;

    private String corpus;

    private boolean includeTeamDriveItems;

    private String orderBy;

    private Integer pageSize;

    private Integer pageToken;

    private String q;

    /**
     * 以逗号分隔的语料库中要查询的空格列表。支持的值为“drive”，“appDataFolder”和“photos”。
     */
    private String spaces;

    private boolean supportsTeamDrives;

    private String teamDriveId;
}

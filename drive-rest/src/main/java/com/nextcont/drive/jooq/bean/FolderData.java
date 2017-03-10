package com.nextcont.drive.jooq.bean;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/7
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
public class FolderData {

    private String fileId;

    private String fileName;

    private String mimeType;

    private String thumbnailLink;

    private String webContentLink;

    private String previewLink;

    private Date createTime;

}

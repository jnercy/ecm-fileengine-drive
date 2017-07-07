package com.nextcont.drive.jooq.bean;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/8
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
public class TransitionUnAggregationData {

    private String fileId;

    private String fileName;

    private Long size;

    private String mimeType;

    private String thumbnailLink;

    private String webContentLink;

    private String previewLink;

    private Date createTime;

    private String md5Checksum;

    private String iconLink;

    private String fullFileExtension;

    private String fileExtension;

    private String parent;

    private long permissionGenId;
}

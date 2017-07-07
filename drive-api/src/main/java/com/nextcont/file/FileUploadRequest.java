package com.nextcont.file;

import lombok.Data;

/**
 * Created by wangxudong on 2017/6/7.
 */
@Data
public class FileUploadRequest {

    private String uploadFileUrl;

    private String parent;

    private String fileId;

    private String fileName;

    private String originalFilename;

    private String shardFileName;

    private Integer shardSize;

    private long fileSize;

    private String md5Checksum;
}

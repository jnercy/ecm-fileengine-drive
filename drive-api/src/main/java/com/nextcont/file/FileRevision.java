package com.nextcont.file;

import lombok.*;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/17
 * Time: 12:58
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileRevision {

    private final String kind = "drive#revision";

    private String id;

    private String mimeType;

    private String modifiedTime;

    private boolean keepForever;

    private boolean published;

    private DriveUser lastModifyingUser;

    private String originalFilename;

    private String md5Checksum;

    private Long size;
}

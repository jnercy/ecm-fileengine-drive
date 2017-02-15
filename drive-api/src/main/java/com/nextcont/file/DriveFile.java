package com.nextcont.file;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/1/11
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriveFile implements Serializable{

    private final String kind = "drive#file";

    private String id;

    private String name;

    private String mimeType;

    private boolean starred;

    private boolean trashed;

    private boolean explicitlyTrashed;

    private List<String> parents;

    private List<String> space;

    private long version;

    private String webContentLink;

    private String webViewLink;

    private String iconLink;

    private boolean hasThumbnail;

    private String thumbnailLink;

    private long thumbnailVersion;

    //锁定状态
    private boolean isLock = false;

    //解锁时间
    private Date deblockingTime;

    private boolean viewedByMe = false;

    private Date viewedByMeTime;

    private Date createdTime;

    private Date modifiedTime;

    private Date modifiedByMeTime;

    private boolean modifiedByMe;

    private Date sharedWithMeTime;

    private DriveUser sharingUser;

    private List<DriveUser> owners;

    private DriveUser lastModifyUser;

    private boolean shared;

    private String ownerId;

    private Capability capabilities;

    private boolean viewersCanCopyContent;

    private boolean writersCanShare;

    private List<FilePermission> permissions;

    private String folderColorRgb;

    private String originalFilename;

    private String fullFileExtension;

    private String fileExtension;

    private String md5Checksum;

    private long size;

    private long quotaBytesUsed;

    private String headRevisionId;

    private boolean isAppAuthorized;
}

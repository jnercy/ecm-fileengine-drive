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

    private String rootId;

    private List<String> spaces;

    private long version;

    private String webContentLink;

    private String webViewLink;

    private String iconLink;

    private boolean hasThumbnail;

    private String thumbnailLink;

    private long thumbnailVersion;

    //是否是文件夹
    private Integer isFolder;

    //锁定状态
    private boolean locked = false;

    //是否手动解锁
    private boolean manuallyUnlock = false;

    //解锁时间
    private String deblockingTime;

    private boolean viewedByMe = false;

    private String viewedByMeTime;

    private String createdTime;

    private String modifiedTime;

    private String modifiedByMeTime;

    private boolean modifiedByMe;

    private List<DriveUser> owners;

    private DriveUser lastModifyUser;

    private boolean shared;

    private boolean ownedByMe;

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

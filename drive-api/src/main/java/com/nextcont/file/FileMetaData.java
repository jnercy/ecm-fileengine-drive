package com.nextcont.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/3
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileMetaData implements Serializable{

    private final String kind = "drive#file";

    private String id;

    private String name;

    private String mimeType;

    private String description;

    private boolean starred;

    private boolean trashed;

    private boolean explicitlyTrashed;

    private List<String> parents;

    private Map properties;

    private Map appProperties;

    private List<String> spaces;

    private Long version;

    private String webContentLink;

    private String webViewLink;

    private String iconLink;

    private boolean hasThumbnail;

    private String thumbnailLink;

    private Long thumbnailVersion;

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

    private ContentHints contentHints;

    private ImageMediaMetadata imageMediaMetadata;

    private VideoMediaMetadata videoMediaMetadata;

    private boolean isAppAuthorized;

    private List<FileProcessRecord> userRecords;

    private List<FileRevision> revisions;
}

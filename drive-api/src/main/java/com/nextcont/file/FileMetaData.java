package com.nextcont.file;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FileMetaData implements Serializable{

    private final String kind = "drive#file";

    private String id;

    private String name;

    private String mimeType;

    private String description;

    private boolean starred;

    private boolean trashed;

    private boolean explicitlyTrashed;

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

    //是否是文件夹
    private Integer isFolder;

    //锁定状态
    private boolean locked = false;

    //是否手动解锁
    private boolean manuallyUnlock = false;

    //解锁时间
    private String deblockingTime;


    private String createdTime;

    private String modifiedTime;

    private List<DriveUser> owners;

    private DriveUser lastModifyUser;

    private boolean shared;

    private Capability capabilities;

    private boolean viewersCanCopyContent;

    private boolean writersCanShare;

    private List<FilePermission> permissions;

    private String folderColorRgb;

    private String originalFilename;

    private String fullFileExtension;

    private String fileExtension;

    private String md5Checksum;

    private Long size;

    private Long quotaBytesUsed;

    private String headRevisionId;

    private ContentHints contentHints;

    private ImageMediaMetadata imageMediaMetadata;

    private VideoMediaMetadata videoMediaMetadata;

    private boolean isAppAuthorized;

    private List<UserRecord> userRecords;

    //private List<FileRevision> revisions;
}

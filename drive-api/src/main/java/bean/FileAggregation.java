package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.ArrayList;
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
@Getter
@AllArgsConstructor
public class FileAggregation implements Serializable{

    @Id
    private String id;

    @Transient
    private final String kind = "drive#file";

    private String fileId;

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

    @Transient
    private boolean viewedByMe = false;

    @Transient
    private Date viewedByMeTime;

    private Date createdTime;

    private Date modifiedTime;

    @Transient
    private Date modifiedByMeTime;

    @Transient
    private boolean modifiedByMe;

    private List<FileOwners> owners;

    private FileOwners lastModifyUser;

    private boolean shared;

    private String ownerId;

    private FileCapability capabilities;

    private boolean viewersCanCopyContent;

    private boolean writersCanShare;

    private List<FilePermission> permissions;

    private String originalFilename;

    private String fullFileExtension;

    private String fileExtension;

    private String md5Checksum;

    private long size;

    private long quotaBytesUsed;

    private String headRevisionId;

    @PersistenceConstructor
    public FileAggregation(String id, String fileId, String name, String mimeType, boolean starred, boolean trashed, boolean explicitlyTrashed, ArrayList<String> parents,
                           ArrayList<String> space, long version, String webContentLink, String webViewLink, String iconLink, boolean hasThumbnail, String thumbnailLink, long thumbnailVersion,
                           Date createdTime, Date modifiedTime, ArrayList<FileOwners> owners, FileOwners lastModifyUser, boolean shared, String ownerId, FileCapability capabilities,
                           boolean viewersCanCopyContent, boolean writersCanShare, ArrayList<FilePermission> permissions, String originalFilename, String fullFileExtension, String fileExtension, String md5Checksum, long size, long quotaBytesUsed, String headRevisionId) {
        this.id = id;
        this.fileId = fileId;
        this.name = name;
        this.mimeType = mimeType;
        this.starred = starred;
        this.trashed = trashed;
        this.explicitlyTrashed = explicitlyTrashed;
        this.parents = parents;
        this.space = space;
        this.version = version;
        this.webContentLink = webContentLink;
        this.webViewLink = webViewLink;
        this.iconLink = iconLink;
        this.hasThumbnail = hasThumbnail;
        this.thumbnailLink = thumbnailLink;
        this.thumbnailVersion = thumbnailVersion;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.owners = owners;
        this.lastModifyUser = lastModifyUser;
        this.shared = shared;
        this.ownerId = ownerId;
        this.capabilities = capabilities;
        this.viewersCanCopyContent = viewersCanCopyContent;
        this.writersCanShare = writersCanShare;
        this.permissions = permissions;
        this.originalFilename = originalFilename;
        this.fullFileExtension = fullFileExtension;
        this.fileExtension = fileExtension;
        this.md5Checksum = md5Checksum;
        this.size = size;
        this.quotaBytesUsed = quotaBytesUsed;
        this.headRevisionId = headRevisionId;
    }
}

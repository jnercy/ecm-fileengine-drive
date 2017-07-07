package com.nextcont.drive.service;

import com.nextcont.drive.aspect.AuthAspect;
import com.nextcont.drive.jooq.bean.TransitionUnAggregationData;
import com.nextcont.drive.utils.BeanUtil;
import com.nextcont.drive.utils.DateTimeUtil;
import com.nextcont.file.*;
import com.nextcont.file.folder.FolderCapability;
import org.bson.Document;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/22
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class FileMetadataMaker {


    private final static long INITIALVERSION = 1L;

    private final static String FOLDER_MIMETYPE = "application/vnd.google-apps.folder";

    private static String nowTime;


    public static Document makeMetaData(TransitionUnAggregationData data) {

        TokenInfo tokenInfo = AuthAspect.getAuthTokenInfo();

        nowTime = DateTimeUtil.nowTime2String();

        List<DriveUser> owners = buildOwner(data,tokenInfo);

        List<FilePermission> permissions = buildFilePermission(data,tokenInfo);

        List<UserRecord> userRecords = buildUserRecord(data,tokenInfo);

        Capability capability = buildCapability(data.getMimeType());

        FileMetaData.FileMetaDataBuilder builder = FileMetaData.builder()
                .id(data.getFileId())
                .name(data.getFileName())
                .spaces(Collections.singletonList("drive"))
                .description(data.getMimeType())
                .mimeType(data.getMimeType())
                .starred(false)
                .trashed(false)
                .explicitlyTrashed(false)
                .version(INITIALVERSION)
                .createdTime(nowTime)
                .modifiedTime(nowTime)
                .owners(owners)
                .lastModifyUser(owners.get(0))
                .shared(false)
                .capabilities(capability)
                .viewersCanCopyContent(true)
                .writersCanShare(true)
                .permissions(permissions)
                .userRecords(userRecords)
                .originalFilename(data.getFileName())
                .headRevisionId("")
                .iconLink("https://cdn10.inecm.cn/iconLink/vnd.google-apps.folder+48.png")
                .isFolder(1);

        if(!data.getMimeType().equals(FOLDER_MIMETYPE)){
            builder.fullFileExtension(data.getFullFileExtension())
                    .fileExtension(data.getFileExtension())
                    .md5Checksum("")
                    .hasThumbnail(true)
                    .thumbnailVersion(INITIALVERSION)
                    .webViewLink(data.getWebContentLink())
                    .iconLink("https://cdn10.inecm.cn/iconLink/plain.png")
                    .isFolder(0);
        }


        return BeanUtil.toBson(builder.build());
    }

    private static Capability buildCapability(String mimeType) {
        return mimeType.equals(FOLDER_MIMETYPE) ? FolderCapability.getInstance() : Capability.getInstance();
    }

    private static List<DriveUser> buildOwner(TransitionUnAggregationData data,TokenInfo tokenInfo) {
        DriveUser owners = DriveUser.builder()
                .displayName(tokenInfo.getGmail())
                .photoLink(tokenInfo.getPhotoLink())
                .permissionId(data.getPermissionGenId())
                .emailAddress(tokenInfo.getGmail())
                .build();
        return Collections.singletonList(owners);
    }

    private static List<FilePermission> buildFilePermission(TransitionUnAggregationData data,TokenInfo tokenInfo) {
        FilePermission permission = FilePermission.builder()
                .id(String.valueOf(data.getPermissionGenId()))
                .type("user")
                .emailAddress(tokenInfo.getGmail())
                .role("owner")
                .displayName(tokenInfo.getGmail())
                .photoLink(tokenInfo.getPhotoLink())
                .build();
        return Collections.singletonList(permission);
    }

    private static List<UserRecord> buildUserRecord(TransitionUnAggregationData data,TokenInfo tokenInfo) {
        UserRecord record = UserRecord.builder()
                .parents(Collections.singletonList(data.getParent()))
                .rootId(tokenInfo.getRootid())
                .viewedByMe(true)
                .viewedByMeTime(nowTime)
                .modifyByMe(true)
                .modifyByMeTime(nowTime)
                .ownedByMe(true)
                .build();
        return Collections.singletonList(record);
    }

}

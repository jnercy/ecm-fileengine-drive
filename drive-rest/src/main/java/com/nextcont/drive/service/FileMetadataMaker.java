package com.nextcont.drive.service;

import com.nextcont.drive.aspect.AuthAspect;
import com.nextcont.drive.jooq.bean.TransitionUnAggregationData;
import com.nextcont.drive.utils.BeanUtil;
import com.nextcont.file.*;
import com.nextcont.file.folder.FolderCapability;
import org.bson.Document;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
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

        nowTime = new DateTime().toString("yyyy-MM-dd");

        List<DriveUser> owners = buildOwner(data,tokenInfo);

        List<FilePermission> permissions = buildFilePermission(data,tokenInfo);

        Capability capability = buildCapability(data.getMimeType());

        FileMetaData.FileMetaDataBuilder builder = FileMetaData.builder();
        FileMetaData fileAgg = builder
                .id(data.getFileId())
                .name(data.getFileName())
                .spaces(Arrays.asList("drive"))
                .description(data.getMimeType())
                .mimeType(data.getMimeType())
                .starred(false)
                .trashed(false)
                .explicitlyTrashed(false)
                .parents(Arrays.asList(AuthAspect.getAuthTokenInfo().getRootid()))
                .version(INITIALVERSION)
                .webViewLink(data.getWebContentLink())
                .hasThumbnail(true)
                .thumbnailVersion(INITIALVERSION)
                .viewedByMeTime(nowTime)
                .createdTime(new DateTime(data.getCreateTime()).toString("yyyy-MM-dd"))
                .modifiedTime(nowTime)
                .modifiedByMeTime(nowTime)
                .modifiedByMe(true)
                .owners(owners)
                .lastModifyUser(owners.get(0))
                .shared(false)
                .capabilities(capability)
                .viewersCanCopyContent(true)
                .writersCanShare(true)
                .permissions(permissions)
                .originalFilename(data.getFileName())
                .fullFileExtension(data.getFullFileExtension())
                .fileExtension(data.getFileExtension())
                .md5Checksum("default")
                .headRevisionId("default")
                .ownedByMe(true)
                .build();

        Document resultDocument = BeanUtil.toBson(fileAgg);

        if(!resultDocument.isEmpty() && data.getMimeType().equals(FOLDER_MIMETYPE)){
            resultDocument.remove("webContentLink");
            resultDocument.remove("webContentLink");
            resultDocument.remove("fullFileExtension");
            resultDocument.remove("fileExtension");
            resultDocument.remove("md5Checksum");
            resultDocument.remove("size");
            if(!fileAgg.isHasThumbnail())
                resultDocument.remove("thumbnailLink");
        }
        return resultDocument;
    }

    private static Capability buildCapability(String mimeType) {
        if (mimeType.equals(FOLDER_MIMETYPE))
            return FolderCapability.getInstance();
        else
            return Capability.getInstance();
    }

    private static List<DriveUser> buildOwner(TransitionUnAggregationData data,TokenInfo tokenInfo) {
        DriveUser.DriveUserBuilder ownersBuilder = DriveUser.builder();
        DriveUser owners = ownersBuilder
                .displayName(tokenInfo.getGmail())
                .photoLink(tokenInfo.getPhotoLink())
                .permissionId(data.getPermissionGenId())
                .emailAddress(tokenInfo.getGmail())
                .build();
        List<DriveUser> ownersArrayList = new ArrayList<>();
        ownersArrayList.add(owners);

        return ownersArrayList;
    }

    private static List<FilePermission> buildFilePermission(TransitionUnAggregationData data,TokenInfo tokenInfo) {

        FilePermission.FilePermissionBuilder permissionBuilder = FilePermission.builder();
        FilePermission permission = permissionBuilder
                .id(String.valueOf(data.getPermissionGenId()))
                .type("user")
                .emailAdddress(tokenInfo.getGmail())
                .role("owner")
                .displayName(tokenInfo.getGmail())
                .photoLink(tokenInfo.getPhotoLink())
                .build();

        return Collections.singletonList(permission);
    }

}

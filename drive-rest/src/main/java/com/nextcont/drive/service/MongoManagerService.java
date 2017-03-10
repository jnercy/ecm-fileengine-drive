package com.nextcont.drive.service;

import com.nextcont.drive.jooq.bean.TransitionUnAggregationData;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.file.*;
import com.nextcont.file.folder.FolderCapability;
import org.bson.Document;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/3/6
 * Time: 16:43
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MongoManagerService {

    @Autowired
    private BaseMongoService fileMetaDataService;

    private final long INITIALVERSION = 1L;

    private final String TEST_USERID = "jnercywang@gmail.com";

    private final String FOLDER_MIMETYPE = "application/vnd.google-apps.folder";

    private String nowTime;

    public void makeMetaData(TransitionUnAggregationData data) {

        nowTime = new DateTime().toString("yyyy-MM-dd");

        List<FileProcessRecord> userRecords = buildUserRecords();

        List<DriveUser> owners = buildOwner();

        List<FilePermission> permissions = buildFilePermission(data);

        Capability capability = buildCapability(data.getMimeType());


        FileMetaData.FileMetaDataBuilder builder = FileMetaData.builder();
        FileMetaData fileAgg = builder
                .id(data.getFileId())
                .name(data.getFileName())
                .spaces(Collections.singletonList("drive"))
                .mimeType(data.getMimeType())
                .starred(false)
                .trashed(false)
                .explicitlyTrashed(false)
                .parents(Collections.singletonList("0AGUv084dR5bdUk9PVA"))
                .version(INITIALVERSION)
                .webViewLink(data.getWebContentLink())
                .iconLink("")
                .hasThumbnail(true)
                .thumbnailVersion(INITIALVERSION)
                .viewedByMeTime(nowTime)
                .createdTime(new DateTime(data.getCreateTime()).toString("yyyy-MM-dd"))
                .modifiedTime(nowTime)
                .modifiedByMeTime(nowTime)
                .modifiedByMe(true)
                .owners(owners)
                .lastModifyUser(owners.get(0))
                .shared(true)
                .capabilities(capability)
                .viewersCanCopyContent(true)
                .writersCanShare(true)
                .permissions(permissions)
                .originalFilename(data.getFileName())
                .fullFileExtension(data.getFullFileExtension())
                .fileExtension(data.getFileExtension())
                .md5Checksum("")
                .size(data.getSize())
                .quotaBytesUsed(data.getSize())
                .headRevisionId("")
                .userRecords(userRecords)
                .build();

            String processRecord = JsonFormat.convertJson(fileAgg)
                .map(insertJson -> {
                    Document doc = Document.parse(insertJson);
                    if (data.getMimeType().equals(FOLDER_MIMETYPE)) {
                        doc.remove("webContentLink");
                        doc.remove("webContentLink");
                        doc.remove("fullFileExtension");
                        doc.remove("fileExtension");
                        doc.remove("md5Checksum");
                        doc.remove("size");
                        if(!fileAgg.isHasThumbnail())
                            doc.remove("thumbnailLink");
                    }
                    fileMetaDataService.insert(doc);
                    return "insert success!!!";
                })
                .orElse("parse error!! please check FileMetaData bean!");


    }


    private List<FileProcessRecord> buildUserRecords() {

        FileProcessRecord.FileProcessRecordBuilder recordBuilder = FileProcessRecord.builder();

        return Collections.singletonList(recordBuilder
                .userId(TEST_USERID)
                .ownedByMe(true)
                .modifyByMe(true)
                .modifyByMeTime(nowTime)
                .viewedByMe(false)
                .viewByMeTime(nowTime)
                .sharedWithMeTime(nowTime)
                .starred(false)
                .build());
    }

    private Capability buildCapability(String mimeType) {
        if (mimeType.equals(FOLDER_MIMETYPE))
            return FolderCapability.getInstance();
        else
            return Capability.getInstance();
    }

    private List<DriveUser> buildOwner() {
        DriveUser.DriveUserBuilder ownersBuilder = DriveUser.builder();
        DriveUser owners = ownersBuilder
                .displayName(TEST_USERID)
                .photoLink("https://lh4.googleusercontent.com/-QL4nj5VHT7E/AAAAAAAAAAI/AAAAAAAAAAo/vP5Ue1hGfpw/s64/photo.jpg")
                .permissionId("14534550892102641987")
                .emailAddress(TEST_USERID)
                .build();
        List<DriveUser> ownersArrayList = new ArrayList<>();
        ownersArrayList.add(owners);

        return ownersArrayList;
    }

    private List<FilePermission> buildFilePermission(TransitionUnAggregationData data) {

        FilePermission.FilePermissionBuilder permissionBuilder = FilePermission.builder();
        FilePermission permission = permissionBuilder
                .id(data.getFileId())
                .type("user")
                .emailAdddress(TEST_USERID)
                .role("owner")
                .displayName(TEST_USERID)
                .photoLink("")
                .build();

        return Collections.singletonList(permission);
    }


}

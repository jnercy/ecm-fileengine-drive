package com.nextcont.drive.tasks;

import com.nextcont.drive.jooq.bean.TransitionUnAggregationData;
import com.nextcont.drive.jooq.service.FileCallbackService;
import com.nextcont.drive.mongo.service.BaseMongoService;
import com.nextcont.file.*;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/7
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
@Component
@Configurable
@EnableScheduling
@Slf4j
public class ScheduledTasks {

    @Autowired
    private FileCallbackService fileCallbackService;

    @Autowired
    private BaseMongoService fileMetaDataService;

    private final long INITIALVERSION = 1L;

    private final String TEST_USERID = "jnercywang@gmail.com";

//    @Scheduled(fixedDelay = 1000 * 5)
    public void generateDataProcess(){
        List<TransitionUnAggregationData>  queryInfo = fileCallbackService.queryTransitionFileInfo();

        if(!queryInfo.isEmpty()) {

            queryInfo.forEach(this::makeMetaData);
            List<String> finishedIds = queryInfo.stream()
                    .map(TransitionUnAggregationData::getFileId)
                    .collect(Collectors.toList());

            fileCallbackService.taskFinishing(finishedIds);
        }

        log.info("Scheduling Tasks Execute finished " +new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        log.info("Scheduling Tasks Count: {} " + queryInfo.size());
    }

    public void makeMetaData(TransitionUnAggregationData data) {
        String nowTime = new DateTime().toString("yyyy-MM-dd");
        FileProcessRecord.FileProcessRecordBuilder recordBuilder = FileProcessRecord.builder();
        List<FileProcessRecord> userRecords = Arrays.asList(recordBuilder
                .userId(TEST_USERID)
                .ownedByMe(true)
                .modifyByMe(true)
                .modifyByMeTime(nowTime)
                .viewedByMe(false)
                .viewByMeTime(nowTime)
                .sharedWithMeTime(nowTime)
                .starred(false)
                .build());

        DriveUser.DriveUserBuilder ownersBuilder = DriveUser.builder();
        DriveUser owners = ownersBuilder
                .displayName(TEST_USERID)
                .photoLink("https://lh4.googleusercontent.com/-QL4nj5VHT7E/AAAAAAAAAAI/AAAAAAAAAAo/vP5Ue1hGfpw/s64/photo.jpg")
                .permissionId("14534550892102641987")
                .emailAddress(TEST_USERID)
                .build();
        List<DriveUser> ownersArrayList = new ArrayList<>();
        ownersArrayList.add(owners);

        Capability.CapabilityBuilder capabilityBuilder = Capability.builder();
        Capability capability = capabilityBuilder
                .canComment(true)
                .canCopy(true)
                .canEdit(true)
                .canShare(true)
                .canReadRevisions(true)
                .build();
        List<Capability> fileCapabilities = new ArrayList<>();
        fileCapabilities.add(capability);

        FilePermission.FilePermissionBuilder permissionBuilder = FilePermission.builder();
        FilePermission permission = permissionBuilder
                .id(data.getFileId())
                .type("user")
                .emailAdddress(TEST_USERID)
                .role("owner")
                .displayName(TEST_USERID)
                .photoLink("")
                .build();

        List<FilePermission> permissions = new ArrayList<>();
        permissions.add(permission);
        List<String> parents = new ArrayList<>();
        parents.add("0AGUv084dR5bdUk9PVA");

        List<String> space = new ArrayList<>();
        space.add("drive");

        FileMetaData.FileMetaDataBuilder builder = FileMetaData.builder();
        FileMetaData fileAgg = builder
                .id(data.getFileId())
                .name(data.getFileName())
                .mimeType(data.getMimeType())
                .starred(false)
                .trashed(false)
                .explicitlyTrashed(false)
                .parents(parents)
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
                .owners(ownersArrayList)
                .lastModifyUser(owners)
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
        fileMetaDataService.insert(fileAgg);
    }
}

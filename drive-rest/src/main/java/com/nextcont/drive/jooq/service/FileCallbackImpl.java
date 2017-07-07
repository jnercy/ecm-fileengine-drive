package com.nextcont.drive.jooq.service;

import com.nextcont.drive.jooq.bean.TransitionUnAggregationData;
import com.nextcont.drive.jooq.db.ecm_trans.Tables;
import com.nextcont.drive.jooq.db.ecm_trans.tables.Transition;
import com.nextcont.drive.jooq.db.ecm_trans.tables.TransitionCallback;
import com.nextcont.drive.jooq.db.ecm_trans.tables.TransitionFile;
import com.nextcont.file.FileType;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author Lukas Eder
 */
@Service
@Slf4j
public class FileCallbackImpl implements FileCallbackService {


    @Autowired
    private DSLContext dsl;

    private final TransitionCallback callback = Tables.TRANSITION_CALLBACK;

    private final Transition transition = Tables.TRANSITION;

    private final TransitionFile transitionFile = Tables.TRANSITION_FILE;

    public List<String> loopAggregationStatus() {

        byte unAggregationsStatus = 0;

        List<String> globalIds = dsl
                .select()
                .from(callback)
                .where(callback.AGGREGATIONSTATUS.equal(unAggregationsStatus).and(callback.STATUS.equal("success")).and(callback.FILEID.isNotNull()))
                .fetch()
                .map(record -> record.get(callback.GLOBALID));

        if(globalIds.size()>0)
            log.info("ungenerated the document data:{}", globalIds.size());

        return globalIds;
    }


    @Override
    public boolean taskFinishing(List<String> ids) {
        byte finishStatus = 1;
        int updateCount =
                dsl.update(callback)
                        .set(callback.AGGREGATIONSTATUS, finishStatus)
                        .where(callback.FILEID.in(ids))
                        .execute();


        log.info("taskFinishingFileCount:{}", updateCount);

        return updateCount > 0;
    }

    @Override
    public List<TransitionUnAggregationData> queryTransitionFileInfo() {
        List<TransitionUnAggregationData> unAggregationFileInfo = new ArrayList<>();
        dsl
                .select()
                .from(transition).leftJoin(transitionFile).on(transition.GLOBALID.eq(transitionFile.GLOBALID))
                .where(transition.GLOBALID.in(loopAggregationStatus()))
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(record -> record.get(transition.GLOBALID)))
                .forEach((k, v) -> {
                    TransitionUnAggregationData.TransitionUnAggregationDataBuilder builder = TransitionUnAggregationData
                            .builder();
                    v.forEach(queryRecord -> {

                        String fileType = queryRecord.get(transitionFile.TYPE);

                        if(fileType==null)
                            fileType = "";

                        builder
                                .fileId(queryRecord.get(transition.FILEID))
                                .fileName(queryRecord.get(transition.FILENAME))
                                .size(queryRecord.get(transition.LENGTH))
                                .mimeType(queryRecord.get(transition.MIMETYPE))
                                .createTime(queryRecord.get(transition.CREATEDATE))
                                .md5Checksum(queryRecord.get(transition.MD5DIGEST))
                                .iconLink(queryRecord.get(transition.ICONLINK));

                        if (fileType.equals(FileType.preview.name())){
                            builder
                                    .webContentLink(queryRecord.get(transitionFile.URL))
                                    .previewLink(queryRecord.get(transitionFile.URL));

                            Optional.ofNullable(queryRecord.get(transition.FILEEXTENSION))
                                    .ifPresent(extension ->
                                            builder
                                                    .fullFileExtension(queryRecord.get(transition.FILEEXTENSION))
                                                    .fileExtension(queryRecord.get(transition.FILEEXTENSION)
                                                            .replace(".", ""))
                                    );
                        } else if (fileType.equals(FileType.thumbnail.name()))
                            builder.thumbnailLink(queryRecord.get(transitionFile.URL));
                    });
                    unAggregationFileInfo.add(builder.build());
                });
        if(unAggregationFileInfo.size()>0)
            log.info("unAggregationFileInfoCount:{}", unAggregationFileInfo.size());
        return unAggregationFileInfo;
    }
}

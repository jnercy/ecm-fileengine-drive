package com.nextcont.drive.tasks;

import com.nextcont.drive.jooq.bean.TransitionUnAggregationData;
import com.nextcont.drive.jooq.service.FileCallbackService;
import com.nextcont.drive.service.MongoManagerService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    private MongoManagerService mongoManagerService;

    @Autowired
    private FileCallbackService fileCallbackService;


    @Scheduled(fixedDelay = 1000 * 60)
    public void generateDataProcess(){
        List<TransitionUnAggregationData>  queryInfo = fileCallbackService.queryTransitionFileInfo();

        if(!queryInfo.isEmpty()) {

            queryInfo.forEach(record->mongoManagerService.refreshTransitionInfo(record));

            List<String> finishedIds = queryInfo
                    .stream()
                    .map(TransitionUnAggregationData::getFileId)
                    .collect(Collectors.toList());

            fileCallbackService.taskFinishing(finishedIds);
        }

        log.info("Scheduling Tasks Execute finished " +new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        log.info("Scheduling Tasks Count: {} " + queryInfo.size());
    }


}

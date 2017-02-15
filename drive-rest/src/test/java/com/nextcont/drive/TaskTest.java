package com.nextcont.drive;

import com.nextcont.drive.tasks.ScheduledTasks;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/2/8
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TaskTest {

    @Autowired
    ScheduledTasks taskService;

    @Test
    public void testTask() throws InterruptedException {
        taskService.generateDataProcess();
    }
}

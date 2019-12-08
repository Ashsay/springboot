package com.single.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ToDoTask {

    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 3000)
    @Scheduled(cron = "1-40 * * * * ? ")
    // http://cron.qqe2.com/
    public void reportCurrentTime(){
        System.out.println("现在的时间是: " + dataFormat.format(new Date()));
    }

}

package com.wlt.springboot.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: T56
 * @Date: 2020-03-26 21:55
 */
@Component
@Slf4j
public class TestQuartz1 {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron="*/5 * * * * ?")
    public void task1(){
        log.info("task1:执行 {}" ,dateFormat.format(new Date()));
    }




    @Scheduled(cron="0 */1 * * * ?")
    public void task2(){
        log.info("===========task2:执行 {}" ,dateFormat.format(new Date()));
    }
}

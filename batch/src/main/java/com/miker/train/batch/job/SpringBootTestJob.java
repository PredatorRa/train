package com.miker.train.batch.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author miker
 * @create 2023-07-16 23:41
 */
@Component
@EnableScheduling
public class SpringBootTestJob {

    @Scheduled(cron = "0/5 * * * * ?")
    public void testScheduled(){
        System.out.println("SpringBootTestJob test");
    }
}

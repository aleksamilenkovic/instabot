package com.instabot.service.impl;

import com.instabot.service.InstaService;
import com.instabot.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.Date;

/**
 * @author lezalekss
 */
@Slf4j
public class SchedulerServiceImpl implements SchedulerService {
    @Autowired
    private InstaService instaService;

    @Scheduled(cron = "0 0 12 * * ?")
    @Override
    public void startInstaLikeService() {
        log.info("Starting likes ...");
        instaService.startLikes();
        log.info("Liking finished at the : " + Calendar.getInstance().getTime());
    }

    @Scheduled(cron = "0 20 12 * * ?")
    @Override
    public void startCollectingStats() {
        log.info("Started to collect statistic ...");
        instaService.collectStats();
        log.info("Collecting statistic finished at the : " + Calendar.getInstance().getTime());
    }


}

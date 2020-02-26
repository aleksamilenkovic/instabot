package com.instabot.service.impl;

import com.instabot.service.InstaScrapperService;
import com.instabot.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;

/**
 * @author lezalekss
 */
@Slf4j
public class SchedulerServiceImpl implements SchedulerService {
    @Autowired
    private InstaScrapperService instaScrapperService;

    @Scheduled(cron = "0 0 12 * * ?")
    @Override
    public void startInstaLikeService() {
        log.info("Starting likes ...");
        instaScrapperService.startLikes();
        log.info("Liking finished at the : " + Calendar.getInstance().getTime());
    }

    @Scheduled(cron = "0 20 12 * * ?")
    @Override
    public void startCollectingStats() {
        log.info("Started to collect statistic ...");
        instaScrapperService.collectStats();
        log.info("Collecting statistic finished at the : " + Calendar.getInstance().getTime());
    }


}

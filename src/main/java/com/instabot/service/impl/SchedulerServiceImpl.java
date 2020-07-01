package com.instabot.service.impl;

import com.instabot.service.InstaScrapperService;
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
    /**
     * Instagram scrapper service
     */
    @Autowired
    private InstaScrapperService instaService;

    /**
     * Method that calls InstaScrapperService
     * to start liking for given profiles
     *
     */
    @Scheduled(cron = "0 0 12 * * ?")
    @Override
    public void startInstaLikeService() {
        log.info("Starting likes ...");
        instaService.startLikes();
        log.info("Liking finished at the : " + Calendar.getInstance().getTime());
    }

    /**
     * Method that calls InstaScrapperService
     * to start collecting statistic for given profiles
     *
     */
    @Scheduled(cron = "0 10 12 * * ?")
    @Override
    public void startCollectingStats() {
        log.info("Started to collect statistic ...");
        instaService.collectStats();
        log.info("Collecting statistic finished at the : " + Calendar.getInstance().getTime());
    }


}

package com.instabot.service;

/**
 * @author lezalekss
 */
public interface SchedulerService {
     /**
      * Method that calls InstaScrapperService
      * to start liking for given profiles
      *
      */
     void startInstaLikeService();

     /**
      * Method that calls InstaScrapperService
      * to start collecting statistic for given profiles
      *
      */
     void startCollectingStats();
}

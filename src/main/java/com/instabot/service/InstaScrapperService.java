package com.instabot.service;

/**
 * @author lezalekss
 */
public interface InstaScrapperService{
    /**
     *
     * Liking profiles that instagram bot follows
     *
     */
    void startLikes();
    /**
     *
     * Collecting statistic for profiles
     * that instagram bot follows
     *
     */
    void collectStats();

}

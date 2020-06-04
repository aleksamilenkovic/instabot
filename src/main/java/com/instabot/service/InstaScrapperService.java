package com.instabot.service;

import com.instabot.domain.InstaProfile;

/**
 * @author lezalekss
 */
public interface InstaScrapperService{

    void startLikes();

    void collectStats();

    InstaProfile addProfile(String username, boolean toLike);
}

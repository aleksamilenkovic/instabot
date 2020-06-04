package com.instabot.service;

import com.instabot.domain.ProfileStats;
import com.instabot.rest.dto.request.ProfileConfig;

/**
 * @author lezalekss
 */
public interface InstaScrapperService{

    void startLikes();

    void collectStats();

    ProfileStats scrapNewProfile(ProfileConfig profileConfig);
}

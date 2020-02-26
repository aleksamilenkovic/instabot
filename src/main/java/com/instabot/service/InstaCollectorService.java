package com.instabot.service;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;

import java.util.List;

/**
 * @author lezalekss
 */
public interface InstaCollectorService {

    List<InstaProfile> getAllProfiles();

    List<ProfileStats> getProfileStatsForProfile(String username);

}

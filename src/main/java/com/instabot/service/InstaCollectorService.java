package com.instabot.service;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;

import java.util.List;

/**
 * @author lezalekss
 */
public interface InstaCollectorService {
    /**
     *
     *  Fetching all insta profiles from InstaProfileRepository
     *
     * @return List<InstaProfile> all instagram profiles as List
     */
    List<InstaProfile> getAllProfiles();
    /**
     *
     *  Fetching all profiles statistic from ProfileStatsRepository
     *
     * @param username (profile username as String)
     *
     * @return profiles statistic as List of ProfileStats
     */
    List<ProfileStats> getAllProfileStatsForProfile(String username);

    /**
     *
     *  Fetching profile statistic from ProfileStatsRepository
     *
     * @param username (profile username as String)
     *
     * @return statistic for given profile as ProfileStats
     */
    ProfileStats getProfileStatsForProfile(String username);

}

package com.instabot.service.impl;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import com.instabot.repository.InstaProfileRepository;
import com.instabot.repository.ProfileStatsRepository;
import com.instabot.service.InstaCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Represents implementation of the InstaCollectorService interface
 * and provide services for fetching from database instagram informations
 * to serve for BotApiController
 * @author lezalekss
 *
 */
@Service
public class InstaCollectorServiceImpl implements InstaCollectorService {

    /**
     * Instagram profile repository that fetchs from db
     */
    @Autowired
    private InstaProfileRepository instaProfileRepository;
    /**
     * Instagram profile stats repository that fetchs from db
     */
    @Autowired
    private ProfileStatsRepository profileStatsRepository;

    /**
     *
     *  Fetching all insta profiles from InstaProfileRepository
     *
     * @return List<InstaProfile> all instagram profiles as List
     */
    @Override
    public List<InstaProfile> getAllProfiles() {
        return instaProfileRepository.findAll();
    }
    /**
     *
     *  Fetching all profiles statistic from ProfileStatsRepository
     *
     * @param username (profile username as String)
     *
     * @return profiles statistic as List of ProfileStats
     */
    @Override
    public List<ProfileStats> getAllProfileStatsForProfile(String username) {
        return profileStatsRepository.findAllByProfile_UsernameOrderByTimeDesc(username);
    }
    /**
     *
     *  Fetching profile statistic from ProfileStatsRepository
     *
     * @param username (profile username as String)
     *
     * @return statistic for given profile as ProfileStats
     */
    @Override
    public ProfileStats getProfileStatsForProfile(String username) {
        return profileStatsRepository.findTopByProfile_UsernameOrderByTimeDesc(username);
    }


}

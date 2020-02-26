package com.instabot.service.impl;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import com.instabot.repository.InstaProfileRepository;
import com.instabot.repository.ProfileStatsRepository;
import com.instabot.service.InstaCollectorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author lezalekss
 */
public class InstaCollectorServiceImpl implements InstaCollectorService {
    @Autowired
    private InstaProfileRepository instaProfileRepository;
    @Autowired
    private ProfileStatsRepository profileStatsRepository;

    @Override
    public List<InstaProfile> getAllProfiles() {
        return instaProfileRepository.findAll();
    }

    @Override
    public List<ProfileStats> getProfileStatsForProfile(String username) {
        return null;
    }
}

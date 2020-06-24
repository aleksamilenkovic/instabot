package com.instabot.service.impl;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import com.instabot.repository.InstaProfileRepository;
import com.instabot.repository.ProfileStatsRepository;
import com.instabot.service.InstaCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lezalekss
 */
@Service
public class InstaCollectorServiceImpl implements InstaCollectorService {

    @Autowired
    private InstaProfileRepository instaProfileRepository;
    @Autowired
    private ProfileStatsRepository profileStatsRepository;

    @Override
    @Cacheable(value = "profiles")
    public List<InstaProfile> getAllProfiles() {
        return instaProfileRepository.findAll();
    }

    @Override
    public List<ProfileStats> getAllProfileStatsForProfile(String username) {
        return profileStatsRepository.findAllByProfile_UsernameOrderByTimeDesc(username);
    }

    @Override
    public ProfileStats getProfileStatsForProfile(String username) {
        return profileStatsRepository.findTopByProfile_UsernameOrderByTimeDesc(username);
    }

    @Override
    @CacheEvict(value = "profiles", allEntries = true)
    public InstaProfile updateProfile(InstaProfile profile) throws Exception {
        if(profile!=null)
            return instaProfileRepository.save(profile);
        throw new Exception("Profile is empty");
    }

    @Override
    @CacheEvict(value = "profiles", allEntries = true)
    public void deleteProfile(InstaProfile profile) {
        instaProfileRepository.deleteById(profile.getUsername());
    }

    @CacheEvict(value = "profiles", allEntries = true)
    public InstaProfile saveProfile(InstaProfile profile){
        return instaProfileRepository.save(profile);
    }

}

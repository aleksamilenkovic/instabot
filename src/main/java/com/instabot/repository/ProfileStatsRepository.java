package com.instabot.repository;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author lezalekss
 */
public interface ProfileStatsRepository extends MongoRepository<ProfileStats,String> {

    ProfileStats findTopByProfile_UsernameOrderByTimeDesc(String username);

    List<ProfileStats> findAllByProfile_UsernameOrderByTimeDesc(String username);
}

package com.instabot.repository;

import com.instabot.domain.ProfileStats;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author lezalekss
 */
public interface ProfileStatsRepository extends MongoRepository<ProfileStats,String> {
}

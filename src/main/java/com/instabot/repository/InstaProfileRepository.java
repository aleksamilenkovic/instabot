package com.instabot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.instabot.domain.InstaProfile;

/**
 * @author lezalekss
 */
public interface InstaProfileRepository extends MongoRepository<InstaProfile, String> {
	InstaProfile findByUsername(String username);
}

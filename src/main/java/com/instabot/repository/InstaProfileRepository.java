package com.instabot.repository;

import com.instabot.domain.InstaProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author lezalekss
 */
public interface InstaProfileRepository extends MongoRepository<InstaProfile, String> {

}

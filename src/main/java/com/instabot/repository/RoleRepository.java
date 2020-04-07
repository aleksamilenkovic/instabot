package com.instabot.repository;

import com.instabot.domain.ERole;
import com.instabot.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author lezalekss
 */
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}

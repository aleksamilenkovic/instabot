package com.instabot.repository;

import com.instabot.InstabotApplicationTests;
import  org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.instabot.domain.InstaProfile;

import static org.junit.Assert.*;


public class DBTest extends InstabotApplicationTests {
	@Autowired
	private InstaProfileRepository profileRepo;

	@Test
	public void testConnection() {
		InstaProfile profile = profileRepo.findByUsername("lezalekss");
		assertEquals("lezalekss", profile.getUsername());
	}

	@Test
	public void testInsertNewProfile(){
		InstaProfile profile = InstaProfile.builder().username("TEST_PROFILE").build();
		// saving profile into db
		profileRepo.save(profile);
		// fetch profile from db and check if equals
		assertEquals("TEST_PROFILE", profileRepo.findByUsername("TEST_PROFILE").getUsername());
		// deleting profile
		profileRepo.delete(profile);
	}

}

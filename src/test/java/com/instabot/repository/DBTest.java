package com.instabot.repository;

import com.instabot.InstabotApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.instabot.InstaServiceTest;
import com.instabot.domain.InstaProfile;
import org.springframework.transaction.annotation.Transactional;


public class DBTest extends InstabotApplicationTests {
	@Autowired
	private InstaProfileRepository profileRepo;

	@Test
	public void testConnection() {
		InstaProfile profile = profileRepo.findByUsername("lezalekss");
		Assert.assertEquals("lezalekss", profile.getUsername());
	}

	@Test
	public void testInsertNewProfile(){
		InstaProfile profile = InstaProfile.builder().username("therock").build();
		Assert.assertEquals("therock", profileRepo.save(profile).getUsername());
	}
}

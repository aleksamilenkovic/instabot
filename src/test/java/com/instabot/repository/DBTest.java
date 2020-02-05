package com.instabot.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.instabot.ConnectionTest;
import com.instabot.domain.InstaProfile;

public class DBTest extends ConnectionTest {
	@Autowired
	private InstaProfileRepository profileRepo;

	@Test
	public void testConnection() {
		InstaProfile profile = profileRepo.findByUsername("lezalekss");
		System.out.println(profile);
	}
}

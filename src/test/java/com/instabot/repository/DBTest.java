package com.instabot.repository;

import com.instabot.InstabotApplicationTests;
import com.instabot.domain.ProfileStats;
import  org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.instabot.domain.InstaProfile;

import java.util.List;

import static org.junit.Assert.*;


public class DBTest extends InstabotApplicationTests {
	@Autowired
	private InstaProfileRepository profileRepo;
	@Autowired
	private ProfileStatsRepository statsRepository;
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

	@Test
	public void getStats(){
		List<ProfileStats> stats = statsRepository.findAll();
		stats.forEach(s ->{
			System.out.println(s.getAverageLikes());
			System.out.println(s.getProfile().getUsername());
		});
	}
}

package com.instabot.repository;

import com.instabot.InstabotApplicationTests;
import com.instabot.domain.ProfileStats;
import  org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.instabot.domain.InstaProfile;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;


public class DBTest extends InstabotApplicationTests {
	@Autowired
	private InstaProfileRepository profileRepo;
	@Autowired
	private ProfileStatsRepository statsRepository;

	@Test
	public void testFetchingProfile() {
		InstaProfile profile = profileRepo.findByUsername("lezalekss");
		assertEquals("lezalekss", profile.getUsername());
	}

	@Test
	public void testInsertNewProfile(){

		InstaProfile profile = InstaProfile.builder().username("d_biilja").build();
		// saving profile into db
		profileRepo.save(profile);
		// fetch profile from db and check if equals
		assertEquals("TEST_PROFILE", profileRepo.findByUsername("TEST_PROFILE").getUsername());
		// deleting profile
		//profileRepo.delete(profile);
	}

	@Test
	public void profileStatsTest(){
		InstaProfile profile = InstaProfile.builder().username("test").build();
		assertNotNull(profileRepo.save(profile));
		ProfileStats ps = ProfileStats.builder().posts(100).followers(600).following(200).averageLikes(122.22).time(LocalDateTime.now()).profile(profile).build();
		assertNotNull(statsRepository.save(ps));
		ProfileStats psFromDB = statsRepository.findTopByProfile_UsernameOrderByTimeDesc("test");
		assertEquals(ps, psFromDB);
		profileRepo.delete(profile);
		statsRepository.delete(psFromDB);
	}

	/*@Test
	public void findAllProfileStats(){
		List<ProfileStats> stats = statsRepository.findAll();
		stats.forEach(System.out::println);
	}


	@Test
	public void findTopByProfile(){
		InstaProfile ip = profileRepo.findByUsername("lezalekss");
		ProfileStats ps = statsRepository.findTopByProfile_UsernameOrderByTimeDesc("lezalekss");
		System.out.println(ps.toString());
	}*/
}

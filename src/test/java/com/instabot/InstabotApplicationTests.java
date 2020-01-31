package com.instabot;

import com.instabot.domain.InstaProfile;
import com.instabot.repository.InstaProfileRepository;
import com.instabot.service.InstaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class InstabotApplicationTests {
	@Autowired
	private InstaService instaService;
	@Autowired
	private InstaProfileRepository profileRepository;

	@Test
	void instaServiceTest(){
		instaService.startLikes();

	}

	@Test
	void mongoDBTest(){
		List<InstaProfile> profiles = profileRepository.findAll();
		profiles.forEach(p-> System.out.println(p.getUsername()));
	}
}

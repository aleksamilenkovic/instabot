package com.instabot.repository;

import com.instabot.InstabotApplicationTests;
import com.instabot.domain.ProfileStats;
import  org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.instabot.domain.InstaProfile;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import static org.junit.Assert.*;


public class DBTest extends InstabotApplicationTests {
	@Autowired
	private InstaProfileRepository profileRepo;
	@Autowired
	private ProfileStatsRepository statsRepository;

	@Test
	public void testFetchingProfile() throws IOException{
		InstaProfile profile = profileRepo.findByUsername("lezalekss");
		profile.setImage(convertImageByte("https://instagram.fbeg1-1.fna.fbcdn.net/v/t51.2885-19/s320x320/43913073_2056866304644254_4326191411889176576_n.jpg?_nc_ht=instagram.fbeg1-1.fna.fbcdn.net&_nc_ohc=ClEc_TBizucAX-bKJIg&edm=ABfd0MgBAAAA&ccb=7-4&oh=c4918886536e5c5102403d4b6573a237&oe=61664963&_nc_sid=7bff83"));
		//profileRepo.save(profile);
		assertEquals("lezalekss", profile.getUsername());
	}

	public static byte[] convertImageByte(String imgUrl) throws IOException {
		URL url = new URL(imgUrl);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = null;
		try {
			is = new BufferedInputStream(url.openStream());
			byte[] byteChunk = new byte[4096];
			int n;
			while ( (n = is.read(byteChunk)) > 0 ) {
				baos.write(byteChunk, 0, n);
			}
			System.out.println(baos);
			return baos.toByteArray();
		}
		catch (IOException e) {e.printStackTrace ();}
		finally {
			if (is != null) { is.close(); }
		}
		return null;
	}


	@Test
	public void testInsertNewProfile() throws IOException {
		String str = "https://instagram.fbeg1-1.fna.fbcdn.net/v/t51.2885-15/e35/p1080x1080/240587119_836857603670273_8255043108348450960_n.jpg?_nc_ht=instagram.fbeg1-1.fna.fbcdn.net&_nc_cat=105&_nc_ohc=1DIjzM3ZUOQAX-FSZ_6&tn=tOsgUU5WMb4pssJ5&edm=AABBvjUBAAAA&ccb=7-4&oh=24e84a1d7fa10bdffae685c615c47ec2&oe=6166FDB1&_nc_sid=83d603";
		URL u = new URL(str);
		InputStream inputStream = u.openStream();
		System.out.println(inputStream.toString()); //-> "Z3VydQ=="
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

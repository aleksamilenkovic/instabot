package com.instabot;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.PostStats;
import com.instabot.domain.ProfileStats;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * @author lezalekss
 */
public class ProfileStatsTest extends InstabotApplicationTests{
    private ProfileStats profileStats;

    @Before
    public void setUp(){
        profileStats = new ProfileStats();
    }

    @After
    public void tearDown(){
        profileStats = null;
    }

    @Test
    public void testSetId(){
        profileStats.setId("FaleffeqF");
        assertEquals("FaleffeqF", profileStats.getId());
    }

    @Test
    public void testSetFollowers(){
        profileStats.setFollowers(520);
        assertEquals(520, profileStats.getFollowers());
    }

    @Test
    public void testSetFollowing(){
        profileStats.setFollowing(103);
        assertEquals(103, profileStats.getFollowing());
    }

    @Test
    public void testSetAverageLikes(){
        profileStats.setAverageLikes(102.2d);
        assertEquals(102.2d, profileStats.getAverageLikes());
    }

    @Test
    public void testSetPosts(){
        profileStats.setPosts(25);
        assertEquals(25, profileStats.getPosts());
    }

    @Test
    public void testSetTime(){
        LocalDateTime time = LocalDateTime.now();
        profileStats.setTime(time);
        assertEquals(time, profileStats.getTime());
    }

    @Test(expected = RuntimeException.class)
    public void testSetTimeError(){
        profileStats.setTime(null);
    }

    @Test
    public void testSetProfile(){
        InstaProfile profile = new InstaProfile();
        profile.setUsername("test");
        profileStats.setProfile(profile);
        assertEquals(profile, profileStats.getProfile());
    }

    @Test(expected = RuntimeException.class)
    public void testSetProfileError(){
        profileStats.setProfile(null);
    }

    @Test
    public void testSetPostsStats(){
        assertEquals(0, profileStats.getPostsStats().size());
        profileStats.getPostsStats().add(new PostStats());
        assertEquals(1, profileStats.getPostsStats().size());
    }
}

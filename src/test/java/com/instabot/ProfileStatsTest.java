package com.instabot;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.PostStats;
import com.instabot.domain.ProfileStats;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author lezalekss
 */
public class ProfileStatsTest {
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
        Assert.assertEquals(102.2d, profileStats.getAverageLikes(), 1);
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
        assertNull(profileStats.getPostsStats());
        List<PostStats> postStats = new ArrayList<>((Arrays.asList(new PostStats(), new PostStats())));
        profileStats.setPostsStats(postStats);
        assertEquals(2, profileStats.getPostsStats().size());
    }
}

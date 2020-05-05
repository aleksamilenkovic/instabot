package com.instabot;

import com.instabot.domain.InstaProfile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author lezalekss
 */
public class InstaProfileTest extends InstabotApplicationTests {
    private InstaProfile profile;

    @Before
    public void setUp(){
        profile = new InstaProfile();
    }

    @After
    public void tearDown(){
        profile = null;
    }

    @Test
    public void testSetUsername(){
        profile.setUsername("lezalekss");
        assertEquals("lezalekss", profile.getUsername());
    }

    @Test(expected = RuntimeException.class)
    public void testSetUsernameError(){
        profile.setUsername(null);
    }

    @Test
    public void testSetFollowers(){
        profile.setFollowers(250);
        assertEquals(250, profile.getFollowers());
    }

    @Test
    public void testSetFollowings(){
        profile.setFollowing(120);
        assertEquals(120, profile.getFollowing());
    }

    @Test
    public void testSetPosts(){
        profile.setPosts(20);
        assertEquals(20, profile.getPosts());
    }

    @Test
    public void testSetImgUrl(){
        profile.setImgUrl("htttps://img-url.com");
        assertEquals("htttps://img-url.com", profile.getImgUrl());
    }

    @Test(expected = RuntimeException.class)
    public void testSetImgUrlError(){
        profile.setImgUrl("htttps:/");
    }

}

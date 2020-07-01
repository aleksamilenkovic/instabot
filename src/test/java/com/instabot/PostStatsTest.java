package com.instabot;

import com.instabot.domain.PostStats;
import org.apache.tomcat.jni.Local;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

/**
 * @author lezalekss
 */
public class PostStatsTest {
    private PostStats postStats;

    @Before
    public void setUp(){
        postStats = new PostStats();
    }

    @After
    public void tearDown(){
        postStats = null;
    }

    @Test
    public void testSetTime(){
        LocalDateTime time = LocalDateTime.now();
        postStats.setTime(time);
        assertEquals(time, postStats.getTime());
    }

    @Test(expected = RuntimeException.class)
    public void tesSetTimeError(){
        postStats.setTime(null);
    }

    @Test
    public void testSetLikes(){
        postStats.setLikes(20);
        assertEquals(20, postStats.getLikes());
    }

    @Test
    public void testSetComments(){
        postStats.setComments(5);
        assertEquals(5, postStats.getComments());
    }

    @Test
    public void testSetUrl(){
        postStats.setUrl("https://instagram.com/lezalekss/tgkv");
        assertEquals("https://instagram.com/lezalekss/tgkv", postStats.getUrl());
    }

    @Test(expected = RuntimeException.class)
    public void tesSetUrlError(){
        postStats.setUrl("http:/");
        postStats.setUrl(null);
    }

    @Test
    public void testSetImgUrl(){
        postStats.setImgUrl("htttps://img-url.com");
        assertEquals("htttps://img-url.com", postStats.getImgUrl());
    }

    @Test(expected = RuntimeException.class)
    public void testSetImgUrlError(){
        postStats.setImgUrl("htttps:/");
    }
}

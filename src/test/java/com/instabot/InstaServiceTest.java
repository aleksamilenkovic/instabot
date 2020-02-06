package com.instabot;

import com.instabot.service.InstaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author lezalekss
 */

public class InstaServiceTest extends InstabotApplicationTests{
    @Autowired
    private InstaService instaService;

    @Test
    public void collectStats(){
        instaService.collectStats();
    }

    @Test
    public void instaServiceTest(){
        instaService.startLikes();

    }
}

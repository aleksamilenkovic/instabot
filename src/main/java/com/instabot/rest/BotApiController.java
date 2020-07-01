package com.instabot.rest;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import com.instabot.service.InstaCollectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lezalekss
 * Represents main rest api for instabot dashboard app and provides specific services.
 *
 */
@Slf4j
@CrossOrigin
@RequestMapping("/api")
@RestController
public class BotApiController {

    @Autowired
    private InstaCollectorService instaCollectorService;

    @GetMapping("/")
    public String hello(){
        return "<html>  <head></head>  <body>" +
                "      <h1>This is the body of the sample view</h1> " +
                "   </body>" +
                "</html>";
    }

    @GetMapping("/get-profiles")
    public List<InstaProfile> getProfiles(){
        log.info("Getting instagram profiles");
        return instaCollectorService.getAllProfiles();
    }

    @GetMapping("/get-profile-stats/{username}")
    public ProfileStats getProfileStats(@PathVariable String username){
        log.info("Getting instagram profile stats for user:" + username);
        return instaCollectorService.getProfileStatsForProfile(username);
    }
}

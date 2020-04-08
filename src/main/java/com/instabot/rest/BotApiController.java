package com.instabot.rest;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import com.instabot.service.InstaCollectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lezalekss
 */
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/bot")
@RestController
public class BotApiController {

    @Autowired
    private InstaCollectorService instaCollectorService;

    @GetMapping("/get-profiles")
    @PreAuthorize("hasAuthority('USER')")
    public List<InstaProfile> getProfiles(){
        log.info("Getting instagram profiles");
        return instaCollectorService.getAllProfiles();
    }

    @GetMapping("/get-profile-stats/{username}")
    @PreAuthorize("hasAuthority('USER')")
    public ProfileStats getProfileStats(@PathVariable String username){
        log.info("Getting instagram profile stats for user:" + username);
        return instaCollectorService.getProfileStatsForProfile(username);
    }
}

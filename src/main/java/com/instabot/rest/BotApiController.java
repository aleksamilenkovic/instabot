package com.instabot.rest;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import com.instabot.rest.dto.request.ProfileConfig;
import com.instabot.service.InstaCollectorService;
import com.instabot.service.InstaScrapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private InstaScrapperService instaScrapperService;

    @GetMapping("/get-profiles")
    @PreAuthorize("hasAuthority('USER') or hasAnyAuthority('ADMIN')")
    public List<InstaProfile> getProfiles(){
        log.info("Getting instagram profiles");
        return instaCollectorService.getAllProfiles();
    }

    @GetMapping("/get-profile-stats/{username}")
    @PreAuthorize("hasAuthority('USER') or hasAnyAuthority('ADMIN')")
    public ProfileStats getProfileStats(@PathVariable String username){
        log.info("Getting instagram profile stats for user:" + username);
        return instaCollectorService.getProfileStatsForProfile(username);
    }


    @PostMapping("/scrap-new-profile")
    @PreAuthorize("hasAuthority('USER') or hasAnyAuthority('ADMIN')")
    public ProfileStats scrapNewProfile(@RequestBody ProfileConfig profileConfig){
        log.info("Scrapping new instagram profile stats for user:" + profileConfig.getUsername());
        return instaScrapperService.scrapNewProfile(profileConfig);
    }
}

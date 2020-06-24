package com.instabot.rest;

import com.instabot.domain.InstaProfile;
import com.instabot.domain.ProfileStats;
import com.instabot.rest.dto.request.ProfileConfig;
import com.instabot.service.InstaCollectorService;
import com.instabot.service.InstaScrapperService;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
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
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<InstaProfile> getProfiles(){
        log.info("Getting instagram profiles");
        return instaCollectorService.getAllProfiles();
    }

    @GetMapping("/get-profile-stats/{username}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ProfileStats getProfileStats(@PathVariable String username){
        log.info("Getting instagram profile stats for user:" + username);
        return instaCollectorService.getProfileStatsForProfile(username);
    }


    @PostMapping("/scrap-new-profile")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ProfileStats scrapNewProfile(@RequestBody ProfileConfig profileConfig){
        log.info("Scrapping new instagram profile stats for user:" + profileConfig.getUsername());
        return instaScrapperService.scrapNewProfile(profileConfig);
    }

    @GetMapping("/delete-profile/{username}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public boolean deleteProfile(@PathVariable String username){
        instaCollectorService.deleteProfile(InstaProfile.builder().username(username).build());
        log.info(username + " - profile deleted");
        return true;
    }

    @PostMapping("/update-profile")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public InstaProfile updateInstaProfile(@RequestBody InstaProfile profile) throws Exception {
        return instaCollectorService.updateProfile(profile);
    }
}

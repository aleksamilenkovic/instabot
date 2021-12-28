package com.instabot.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instabot.domain.InstaProfile;
import com.instabot.domain.PostStats;
import com.instabot.domain.ProfileStats;
import com.instabot.rest.dto.request.ProfileConfig;
import com.instabot.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.instabot.util.Utils.convertImageToByte;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author lezalekss
 */
@Slf4j
@Component
public class InstaParser {
    private final String profileInfoApi = "https://www.instagram.com/%s/channel/?__a=1";
    private final String postUrlTemplate = "https://www.instagram.com/p/";

    public ProfileStats scrapNewProfile(ProfileConfig profileConfig){
        RestTemplate template = new RestTemplate();
        ProfileStats profileStats = new ProfileStats();
        String profileInfoUrl = String.format(profileInfoApi, profileConfig.getUsername());
        JsonNode user;
        try {
            ResponseEntity<String> response = template.getForEntity(profileInfoUrl, String.class);
            user = (new ObjectMapper()).readTree(Objects.requireNonNull(response.getBody())).get("graphql").get("user");
            InstaProfile profile = scrapProfile(user, profileConfig);
            profileStats.setProfile(profile);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Error with parsing json from instagram api for user: " +profileConfig.getUsername());
        }
        if(!profileStats.getProfile().isPrivate())
            scrapStats(user.get("edge_owner_to_timeline_media").get("edges"), profileStats);
        log.info(profileStats.toString());
        return profileStats;
    }

    private InstaProfile scrapProfile(JsonNode user, ProfileConfig profileConfig){
        boolean isPrivate = user.get("is_private").booleanValue();
        String imgUrl = user.get("profile_pic_url_hd").asText();
        int followers = user.get("edge_followed_by").get("count").intValue();
        int following = user.get("edge_follow").get("count").intValue();
        int posts = user.get("edge_owner_to_timeline_media").get("count").intValue();
        InstaProfile profile = InstaProfile.builder()
                .username(profileConfig.getUsername())
                .isPrivate(isPrivate)
                .toLike(profileConfig.isToLike())
                .imgUrl(imgUrl).followers(followers)
                .following(following)
                .posts(posts)
                .image(convertImageToByte(imgUrl))
                .build();
        return profile;
    }

    private void scrapStats(JsonNode posts, ProfileStats stats){
        List<PostStats> postStats = new LinkedList<>();
        int sumLikes=0;
        int recentPosts=0;
        try{
            for(JsonNode node : posts){
                node = node.get("node");
                PostStats ps = new PostStats();
                String imgUrl = node.get("thumbnail_src").asText();
                String postUrl = postUrlTemplate + node.get("shortcode").asText();
                int likes = node.get("edge_liked_by").get("count").intValue();
                int comments = node.get("edge_media_to_comment").get("count").intValue();
                LocalDateTime time = Utils.getDateFromTimestamp(node.get("taken_at_timestamp").longValue());
                postStats.add(PostStats.builder().time(time).imgUrl(imgUrl).url(postUrl).likes(likes).comments(comments).image(convertImageToByte(imgUrl)).build());
                sumLikes+=likes;
                recentPosts++;
            }
            stats.setPostsStats(postStats);
            stats.setAverageLikes((double)sumLikes/recentPosts);
            stats.setTime(LocalDateTime.now());
            stats.setFollowing(stats.getProfile().getFollowing());
            stats.setFollowers(stats.getProfile().getFollowers());
            stats.setPosts(stats.getProfile().getPosts());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Error with parsing json from instagram api for user: " +stats.getProfile().getUsername());
        }
    }
}

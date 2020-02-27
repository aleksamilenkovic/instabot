package com.instabot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
/**
 * @author lezalekss
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "profile")
public class InstaProfile {
    @Id
    @Field("username")
    private String username;
    // these fields are current state and updated daily
    @Field("followers")
    private int followers;
    @Field("following")
    private int following;
    @Field("posts")
    private int posts;
    @Field("img")
    private String imgUrl;

    public void update(int followers, int following, int posts, String imgUrl){
        this.followers = followers;
        this.following = following;
        this.posts = posts;
        this.imgUrl = imgUrl;
    }
}

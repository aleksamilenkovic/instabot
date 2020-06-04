package com.instabot.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Field("to_like")
    private boolean toLike;
    @Field("is_private")
    private boolean isPrivate;

    public void update(int followers, int following, int posts, String imgUrl, boolean toLike, boolean isPrivate){
        this.followers = followers;
        this.following = following;
        this.posts = posts;
        this.imgUrl = imgUrl;
        this.toLike = toLike;
        this.isPrivate = isPrivate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstaProfile that = (InstaProfile) o;
        return Objects.equal(username, that.username);
    }

}

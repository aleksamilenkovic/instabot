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
 * Represents instagram profile with crud informations about profile
 *  -> informations that everyone can see
 */
@Builder
@Document(collection = "profile")
public class InstaProfile {
    /**
     * Profile username
     */
    @Id
    @Field("username")
    private String username;
    /**
     * Profile followers
     */
    // these fields are current state and updated daily
    @Field("followers")
    private int followers;
    /**
     * Profile following
     */
    @Field("following")
    private int following;
    /**
     * Number of posts
     */
    @Field("posts")
    private int posts;
    /**
     * Profile image url
     */
    @Field("img")
    private String imgUrl;

    /**
     *
     * @return profile username as String.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets new username.
     *
     * @param username (new username to set)
     *
     */
    public void setUsername(String username) {
        if(username==null || username.length() < 4)
            throw new RuntimeException("Wrong username entered");
        this.username = username;
    }
    /**
     *
     * @return profile followers as int.
     */
    public int getFollowers() {
        return followers;
    }
    /**
     * Sets new profile followers.
     *
     * @param followers (new followers to set)
     *
     */
    public void setFollowers(int followers) {
        this.followers = followers;
    }
    /**
     *
     * @return profile number of followings as int.
     */
    public int getFollowing() {
        return following;
    }
    /**
     * Sets new profile followings.
     *
     * @param following (new number of followings to set)
     *
     */
    public void setFollowing(int following) {
        this.following = following;
    }
    /**
     *
     * @return profile posts as int.
     */
    public int getPosts() {
        return posts;
    }
    /**
     * Sets new profile posts.
     *
     * @param posts (new number of posts to set)
     *
     */
    public void setPosts(int posts) {
        this.posts = posts;
    }
    /**
     *
     * @return profile image url as String.
     */
    public String getImgUrl() {
        return imgUrl;
    }
    /**
     * Sets new image url.
     *
     * @param imgUrl
     *
     */
    public void setImgUrl(String imgUrl) {
        if(imgUrl == null || imgUrl.length() < 10)
            throw new RuntimeException("Image url is not correct (null or doesn't exist)");
        this.imgUrl = imgUrl;
    }
    /**
     * Updates the profile with params followers, following, posts, imgUrl
     * in that order.
     */
    public void update(int followers, int following, int posts, String imgUrl){
        setFollowers(followers);
        setFollowing(following);
        setPosts(posts);
        setImgUrl(imgUrl);
    }
    /**
     * Checks if 2 profiles are the same and
     *  returns true if they are or false if not
     *
     * @return true if 2 profiles have the same username
     * false if they dont have.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstaProfile that = (InstaProfile) o;
        return Objects.equal(username, that.username);
    }

    /**
     * empty consturctor
     */
    public InstaProfile() { }

    public InstaProfile(String username, int followers, int following, int posts, String imgUrl) {
        setUsername(username);
        setFollowers(followers);
        setFollowing(following);
        setPosts(posts);
        setImgUrl(imgUrl);
    }
}

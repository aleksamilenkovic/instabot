package com.instabot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lezalekss
 * Represents instagram profile statistics with statistic by post.
 * It is scrapped at certain time.
 */
@Builder
@Document(collection = "profile_stats")
public class ProfileStats {
    /**
     * Generated profile id
     */
    @Id
    @JsonIgnore
    private String id;
    /**
     * Profile followers
     */
    @Field("followers")
    private int followers;
    /**
     * Profile following
     */
    @Field("following")
    private int following;
    /**
     * Profile posts
     */
    @Field("posts")
    private int posts;
    /**
     * Average profile likes
     */
    @Field("average_likes")
    private double averageLikes;
    /**
     * Time when stats is calculated
     */
    @Field("time")
    private LocalDateTime time;
    /**
     * List of stats by every post
     */
    @Field("posts_stats")
    private List<PostStats> postsStats;
    /**
     * instagram profile for stats
     */
    @DBRef
    private InstaProfile profile;

    /**
     * empty constructor
     */
    public ProfileStats() {
    }

    /**
     * full constructor
     * @param id
     * @param followers
     * @param following
     * @param posts
     * @param averageLikes
     * @param time
     * @param postsStats
     * @param profile
     */
    public ProfileStats(String id, int followers, int following, int posts, double averageLikes, LocalDateTime time, List<PostStats> postsStats, InstaProfile profile) {
        this.id = id;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
        this.averageLikes = averageLikes;
        this.time = time;
        this.postsStats = postsStats;
        this.profile = profile;
    }

    /**
     *
     * @return id as String
     */
    public String getId() {
        return id;
    }
    /**
     * Sets new profilestats id.
     *
     * @param id (new id to set)
     *
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     *
     * @return followers as int
     */
    public int getFollowers() {
        return followers;
    }
    /**
     * Sets new followers.
     *
     * @param followers (new followers to set)
     *
     */
    public void setFollowers(int followers) {
        this.followers = followers;
    }
    /**
     *
     * @return followings as int
     */
    public int getFollowing() {
        return following;
    }
    /**
     * Sets new followings.
     *
     * @param following (new followings to set)
     *
     */
    public void setFollowing(int following) {
        this.following = following;
    }
    /**
     *
     * @return posts as int
     */
    public int getPosts() {
        return posts;
    }
    /**
     * Sets new number of posts .
     *
     * @param posts (new posts to set)
     *
     */
    public void setPosts(int posts) {
        this.posts = posts;
    }
    /**
     *
     * @return average profile likes as double
     */
    public double getAverageLikes() {
        return averageLikes;
    }
    /**
     * Sets new average likes
     *
     * @param averageLikes (new avg likes to set)
     *
     */
    public void setAverageLikes(double averageLikes) {
        this.averageLikes = averageLikes;
    }
    /**
     *
     * @return time calculated as LocalDateTime
     */
    public LocalDateTime getTime() {
        return time;
    }
    /**
     * Sets new time calculated .
     *
     * @param time (new time to set)
     *
     */
    public void setTime(LocalDateTime time) {
        if(time == null)
            throw new RuntimeException("Time can't be null");
        this.time = time;
    }
    /**
     *
     * @return posts stats as list of PostStats
     */
    public List<PostStats> getPostsStats() {
        return postsStats;
    }
    /**
     * Sets new posts stats .
     *
     * @param postsStats (new posts stats as list to set)
     *
     */
    public void setPostsStats(List<PostStats> postsStats) {
        this.postsStats = postsStats;
    }
    /**
     *
     * @return profile as InstaProfile
     */
    public InstaProfile getProfile() {
        return profile;
    }
    /**
     * Sets new instagram profile for stats .
     *
     * @param profile (new posts to set)
     *
     */
    public void setProfile(InstaProfile profile) {
        if(profile == null)
            throw new RuntimeException("Profile can't be null");
        this.profile = profile;
    }

    /**
     * Checks if 2 profile stats are the same and
     *  returns true if they are or false if not
     *
     * @return true if 2 profile stats have the same profile
     * and calculated at same time
     * false if they didn't.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileStats that = (ProfileStats) o;
        return Objects.equal(time, that.time) &&
                Objects.equal(profile, that.profile);
    }
}

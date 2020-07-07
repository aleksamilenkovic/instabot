package com.instabot.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * Represents post statistic from ProfileStats with post's basic informations.
 * @author lezalekss
 */
@Builder
public class PostStats {
    /**
     * Post time uploaded
     */
    @Field("uploaded_date")
    private LocalDateTime time;
    /**
     * Post likes
     */
    @Field("likes")
    private int likes;
    /**
     * Post comments
     */
    @Field("comments")
    private int comments;

    /**
     * Post url
     */
    @Field("url")
    private String url;

    /**
     * Post image url
     */
    @Field("img")
    private String imgUrl;
    /**
     *
     * @return post time uploaded as LocalDateTime
     */
    public LocalDateTime getTime() {
        return time;
    }
    /**
     * Sets new post uploaded time.
     *
     * @param time (new time to set)
     *
     */
    public void setTime(LocalDateTime time) {
        if(time == null)
            throw new RuntimeException("Time is null");
        this.time = time;
    }
    /**
     *
     * @return post number of likes as int
     */
    public int getLikes() {
        return likes;
    }
    /**
     * Sets new likes.
     *
     * @param likes (new likes to set)
     *
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }
    /**
     *
     * @return post number of comments as int
     */
    public int getComments() {
        return comments;
    }
    /**
     * Sets new comments.
     *
     * @param comments (new comments to set)
     *
     */
    public void setComments(int comments) {
        this.comments = comments;
    }
    /**
     *
     * @return post url as String.
     */
    public String getUrl() {
        return url;
    }
    /**
     * Sets new profile url.
     *
     * @param url (new url to set)
     *
     */
    public void setUrl(String url) {
        if(url == null || url.length() < 10)
            throw new RuntimeException("Url is not correct (null or doesn't exist)");
        this.url = url;
    }
    /**
     *
     * @return post image url as String.
     */
    public String getImgUrl() {
        return imgUrl;
    }
    /**
     * Sets new imgUrl.
     *
     * @param imgUrl (new imgUrl to set)
     *
     */
    public void setImgUrl(String imgUrl) {
        if(imgUrl == null || imgUrl.length() < 10)
            throw new RuntimeException("Image url is not correct (null or doesn't exist)");
        this.imgUrl = imgUrl;
    }

    public PostStats(){}

    public PostStats(LocalDateTime time, int likes, int comments, String url, String imgUrl) {
        this.time = time;
        this.likes = likes;
        this.comments = comments;
        this.url = url;
        this.imgUrl = imgUrl;
    }
}

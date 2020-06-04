package com.instabot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * @author lezalekss
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostStats {
    @Field("uploaded_date")
    private LocalDateTime time;
    @Field("likes")
    private int likes;
    @Field("comments")
    private int comments;
    @Field("url")
    private String url;
    @Field("img")
    private String imgUrl;
}

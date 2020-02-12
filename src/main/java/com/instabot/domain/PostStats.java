package com.instabot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * @author lezalekss
 */
@Data
@NoArgsConstructor
@SuperBuilder
@Document(collection = "post_stats")
public class PostStats {
    @Field("uploaded_date")
    private LocalDateTime time;
    @Field("likes")
    private int likes;
    @Field("comments")
    private int comments;
    @Field("url")
    private String url;

}

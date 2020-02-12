package com.instabot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lezalekss
 */
@Data
@NoArgsConstructor
@SuperBuilder
@Document(collection = "profile_stats")
public class ProfileStats {
    @Field("followers")
    private int followers;
    @Field("following")
    private int following;
    @Field("posts")
    private int posts;
    @Field("average_likes")
    private double averageLikes;
    @Field("time")
    private LocalDateTime time;
    @Field("posts_stats")
    private List<PostStats> postsStats;
    @DBRef
    private InstaProfile profile;
}

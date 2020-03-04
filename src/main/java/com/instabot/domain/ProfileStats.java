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
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "profile_stats")
public class ProfileStats {
    @Id
    @JsonIgnore
    private String id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileStats that = (ProfileStats) o;
        return Objects.equal(time, that.time) &&
                Objects.equal(profile, that.profile);
    }
}

package com.instabot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author lezalekss
 */
@Data
@NoArgsConstructor
@SuperBuilder
@Document(collection = "insta_profile")
public class InstaProfile {
    @Field("username")
    private String username;
}

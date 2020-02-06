package com.instabot.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
/**
 * @author lezalekss
 */
@Data
@NoArgsConstructor
@SuperBuilder
@Document(collection = "profile")
public class InstaProfile {
    @Id
    private String id;

    @Field("username")
    @Indexed(unique=true)
    private String username;
}

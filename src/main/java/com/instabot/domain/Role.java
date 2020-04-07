package com.instabot.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author lezalekss
 */
@Document(collection = "role")
public class Role {
    @Id
    private String id;

    private ERole role;

    public Role() {
    }

    public Role(ERole role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
}

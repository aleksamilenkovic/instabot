package com.instabot.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lezalekss
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileConfig {
    private String username;
    private boolean toLike;
    private boolean toFollow;
}

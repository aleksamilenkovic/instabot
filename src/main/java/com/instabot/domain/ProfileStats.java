package com.instabot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lezalekss
 */
@Data
@NoArgsConstructor
public class ProfileStats {
    private int followers;
    private int following;
    private int posts;
    private double averageLikes;
}

package com.instabot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ProfileStats {
	private int followers;
	private int following;
	private double averagePostLikes;
}

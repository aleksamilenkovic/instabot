package com.instabot.config;

import com.instabot.service.SchedulerService;
import com.instabot.service.impl.SchedulerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * @author lezalekss
 */
@Configuration
@EnableScheduling
public class AppConfig {
    @Bean
    public SchedulerService scheduler(){
        return new SchedulerServiceImpl();
    }

}

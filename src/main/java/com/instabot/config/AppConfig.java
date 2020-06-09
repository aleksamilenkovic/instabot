package com.instabot.config;

import com.instabot.service.SchedulerService;
import com.instabot.service.impl.SchedulerServiceImpl;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * @author lezalekss
 */
@Configuration
@EnableScheduling
@EnableAsync
@EnableCaching
public class AppConfig {
    @Bean
    public SchedulerService scheduler(){
        return new SchedulerServiceImpl();
    }

}

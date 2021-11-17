package com.zai.weather.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CaffeineConfiguration {
    @Bean
    public Caffeine caffeineConfig() {
        //set the cache lifetime is 3 sec after write
        return Caffeine.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.getCache("weathers");
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}


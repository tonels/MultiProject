package com.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redisson() throws IOException {

        // 两种读取方式，Config.fromYAML 和 Config.fromJSON
//        Config config = Config.fromJSON(RedissonConfig.class.getClassLoader().getResource("redisson-config.json"));
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-config.yml"));
        return Redisson.create(config);
    }
}

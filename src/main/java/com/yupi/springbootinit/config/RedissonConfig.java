package com.yupi.springbootinit.config;
/*
 * Author: 咸余杨
 * */


import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 实现分布式限流
* */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redisson")
public class RedissonConfig {
    private Integer database;
    private String host;
    private Integer port;
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setDatabase(1)
                .setAddress("redis://"+ host + ":" + port)
                .setPassword(password);

        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}

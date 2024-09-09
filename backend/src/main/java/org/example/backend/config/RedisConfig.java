/**
 * CreateTime: 2024-08-30
 * Description: Redis的配置文件
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    private final static String key = "CodeNote:";

    public static String getKey() {
        return key; // 通过公共方法在类内部访问 private 属性
    }


    // Bean redis 用来调用
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {

        // 配置 redis 配置
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();

        Dotenv dotenv = Dotenv.configure().directory("backend/.env").load(); // 加载 .env 文件
        redisConfig.setHostName(dotenv.get("REDIS_HOST"));
        redisConfig.setPort(Integer.parseInt(dotenv.get("REDIS_PORT")));
        redisConfig.setPassword(dotenv.get("REDIS_PASSWORD"));

        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisConfig);
        connectionFactory.afterPropertiesSet();

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 配置序列化器
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}

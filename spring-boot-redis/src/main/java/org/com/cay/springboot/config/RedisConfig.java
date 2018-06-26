package org.com.cay.springboot.config;

import org.com.cay.springboot.entity.User;
import org.com.cay.springboot.serializer.RedisObjectSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by Cay on 2017/9/26.
 */
@Configuration
public class RedisConfig {

	@Autowired
	public JedisConnectionFactory jedisConnectionFactory;

	@Bean
	public RedisTemplate<String, User> redisTemplate(){
		RedisTemplate<String, User> userRedisTemplate = new RedisTemplate<>();

		userRedisTemplate.setConnectionFactory(jedisConnectionFactory);
		userRedisTemplate.setKeySerializer(new StringRedisSerializer());
		userRedisTemplate.setValueSerializer(new RedisObjectSerializer());

		return userRedisTemplate;
	}
}

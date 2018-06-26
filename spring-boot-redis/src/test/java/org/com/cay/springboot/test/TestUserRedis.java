package org.com.cay.springboot.test;

import org.com.cay.springboot.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Cay on 2017/9/26.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserRedis {

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Test
	public void testUserRedis(){
		User user = new User();
		user.setUsername("Cay");
		user.setAge(20);

		redisTemplate.opsForValue().set(user.getUsername(), user);

		Assert.assertEquals(20, redisTemplate.opsForValue().get("Cay").getAge());
	}
}

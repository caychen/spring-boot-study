package org.com.cay.springboot.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Cay on 2017/9/26.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedis {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testStringRedis(){
		stringRedisTemplate.opsForValue().set("name","Cay");

		Assert.assertEquals("Cay", stringRedisTemplate.opsForValue().get("name"));
	}
}

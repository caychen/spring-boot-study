package org.com.cay.springboot.test;

import org.com.cay.springboot.entity.Person;
import org.com.cay.springboot.service.IPersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

/**
 * Created by Cay on 2017/11/17.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@EnableCaching//开启缓存功能
public class TestCacheApplication {

	@Autowired
	private IPersonService personService;

	@Autowired
	private CacheManager cacheManager;

	@Test
	public void test(){
		System.out.println(cacheManager.getClass().getName());//EhCacheCacheManager

		Person p1 = personService.findPersonByName("Cay");
		System.out.println("第一次查询：" + p1.getAge());

		Person p2 = personService.findPersonByName("Cay");
		System.out.println("第二次查询：" + p2.getAge());

		Random r = new Random(new Date().getTime());
		p2.setAge(r.nextInt(100));
		personService.save(p2);//此时会更新ehcache缓存中的数据

		Person p3 = personService.findPersonByName("Cay");
		System.out.println("第三次查询：" + p3.getAge());//此时查询到的数据是更新后的值
	}

}

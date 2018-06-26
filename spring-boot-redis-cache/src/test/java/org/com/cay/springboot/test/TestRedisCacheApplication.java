package org.com.cay.springboot.test;

import org.com.cay.springboot.entity.Person;
import org.com.cay.springboot.repository.IPersonRepository;
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
public class TestRedisCacheApplication {

	@Autowired
	private IPersonService personService;

	@Autowired
	private CacheManager cacheManager;

	@Test
	public void test(){
		System.out.println(cacheManager.getClass().getName());//RedisCacheManager

		//问题：如果当前数据库中id为2的age值为25

		//第一次查询的时候去数据库中查找name为Cay的Person对象，查询到的结果放入redis缓存中
		Person p1 = personService.findPersonByName("Cay");
		System.out.println("第一次查询：" + p1.getAge());

		//第二次查询的时候直接从redis缓存中获取
		Person p2 = personService.findPersonByName("Cay");
		System.out.println("第二次查询：" + p2.getAge());

		Random r = new Random(new Date().getTime());
		p2.setAge(r.nextInt(100));
		personService.save(p2);//只会更新数据库中的数据，而不会更新redis缓存中的数据
		//解决方案：重写save函数，并写上@CachePut注解

		//第三次查询依然还会去redis缓存中查询，而不是从数据库中查询，造成数据的不一致性。因为Redis的缓存是独立于应用的，无法在更新数据库之后去通知redis也更新缓存数据，而ehcache是进程间的缓存框架，当更新数据之后，也会同时更新ehcache缓存中的数据。
		Person p3 = personService.findPersonByName("Cay");
		System.out.println("第三次查询：" + p3.getAge());

	}

}

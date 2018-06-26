package org.com.cay.springboot.test;

import org.com.cay.springboot.entity.User;
import org.com.cay.springboot.repository.IUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Cay on 2017/9/27.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMongodb {

	@Autowired
	private IUserRepository userRepository;

	@Test
	public void test(){
		userRepository.save(new User(1,"Cay",20));
		userRepository.save(new User(2,"Amy",14));
		userRepository.save(new User(3,"Kitty",30));

		Assert.assertEquals(3, userRepository.findAll().size());

		//删除一个User
		User one = userRepository.findOne(1);
		userRepository.delete(one);
		Assert.assertEquals(2, userRepository.findAll().size());

		//再删除一个User
		User two = userRepository.findByUsername("Kitty");
		userRepository.delete(two);
		Assert.assertEquals(1, userRepository.findAll().size());

	}
}

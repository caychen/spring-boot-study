package org.com.cay.springboot.test;

import org.com.cay.springboot.entity.Person;
import org.com.cay.springboot.repository.IPersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Cay on 2017/11/17.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestServices {

	@Autowired
	private IPersonRepository personRepository;

	@Test
	public void test(){
		Person p1 = personRepository.findPersonByName("Cay");
		System.out.println("第一次查询：" + p1.getAge());

		Person p2 = personRepository.findPersonByName("Cay");
		System.out.println("第二次查询：" + p2.getAge());
	}
}

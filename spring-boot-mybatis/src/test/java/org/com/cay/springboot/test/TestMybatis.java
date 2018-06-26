package org.com.cay.springboot.test;

import org.com.cay.springboot.entity.Person;
import org.com.cay.springboot.entity.Product;
import org.com.cay.springboot.mapper.IPersonMapper;
import org.com.cay.springboot.mapper.IProductMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Random;

/**
 * Created by Cay on 2017/12/19.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@MapperScan(basePackages = {"org.com.cay.springboot.mapper"})//可以扫描多个包
public class TestMybatis {

	@Autowired
	private IProductMapper productMapper;

	@Autowired
	private IPersonMapper personMapper;

	@Test
	public void testMybatis1(){
		productMapper.deleteAll();

		productMapper.insert("aaa", 12.5);

		Product product = productMapper.findByName("aaa");

		Assert.assertEquals(java.util.Optional.of(12.5), java.util.Optional.of(product.getPrice()));

	}

	@Test
	public void testMybatis2(){
		List<Person> all = personMapper.getAll();
		all.stream().forEach(System.out::println);

		System.out.println("----------插入数据前--------------");
		for (int i = 0; i < 100; i++) {
			Person person = new Person();
			Random r = new Random();
			person.setAge(r.nextInt(50));
			person.setUsername("a" + r.nextInt(100));

			personMapper.addPerson(person);
		}
		System.out.println("----------插入数据后--------------");

		all = personMapper.getAll();
		all.stream().forEach(System.out::println);

//		System.out.println("----------查询name带有e的数据--------------");
//		all = personMapper.getByNameLike("%e%");
//		all.stream().forEach(System.out::println);
//
//		System.out.println("-----------更新数据-------------");
//		Person person2 = new Person();
//		person2.setId(3);
//		person2.setAge(24);
//		personMapper.updatePerson(person2);
//		all = personMapper.getAll();
//		all.stream().forEach(System.out::println);
//
//		System.out.println("------------------------");
//		personMapper.deletePerson(3);
//		all = personMapper.getAll();
//		all.stream().forEach(System.out::println);

	}

	@Test
	public void testMybatis3(){
		Person person = new Person();
		Random r = new Random();
		person.setAge(r.nextInt(50));
		person.setUsername("a" + r.nextInt(100));

		personMapper.addPerson(person);

		System.out.println(person);
	}
}

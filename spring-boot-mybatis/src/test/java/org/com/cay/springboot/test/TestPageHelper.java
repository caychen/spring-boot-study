package org.com.cay.springboot.test;

import com.github.pagehelper.PageHelper;
import org.com.cay.springboot.entity.Person;
import org.com.cay.springboot.mapper.IPersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Cay on 2018/4/8.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@MapperScan(basePackages = "org.com.cay.springboot.mapper")
public class TestPageHelper {

	@Autowired
	private IPersonMapper personMapper;

	@Test
	public void testPage(){
		//设置页号及每页记录数
		PageHelper.startPage(2, 3);

		List<Person> all = personMapper.getAll();
		all.stream().forEach(System.out::println);
	}
}

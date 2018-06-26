package org.com.cay.springboot;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.com.cay.springboot.entity.Person;
import org.com.cay.springboot.properties.DruidProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAnnotationPropertiesApplicationTests {

	@Autowired
	private Person person;

	@Autowired
	private ApplicationContext ctx;

	@Test
	public void testPerson() {
		System.out.println(person);
	}

	@Test
	public void testDbConfig(){
//		BasicDataSource dataSource1 = ctx.getBean(BasicDataSource.class);
//		System.out.println(dataSource1);
//
//		ComboPooledDataSource dataSource2 = ctx.getBean(ComboPooledDataSource.class);
//		System.out.println(dataSource2);

		DruidDataSource dataSource3 = ctx.getBean(DruidDataSource.class);
		System.out.println(dataSource3);
	}

	@Test
	public void testProperties(){
		DruidProperties properties = ctx.getBean(DruidProperties.class);
		System.out.println(properties);
	}

}

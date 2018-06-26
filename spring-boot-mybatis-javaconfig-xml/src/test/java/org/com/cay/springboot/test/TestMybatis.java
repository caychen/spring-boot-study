package org.com.cay.springboot.test;

import org.com.cay.springboot.entity.Product;
import org.com.cay.springboot.mapper.IProductMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Cay on 2017/12/12.
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public class TestMybatis {

	@Autowired
	private IProductMapper productMapper;

	@Test
	public void testMybatis1(){

		productMapper.deleteAll();

		productMapper.insert("aaa", 12.5);

		Product product = productMapper.findByName("aaa");

		Assert.assertEquals(java.util.Optional.of(12.5), java.util.Optional.of(product.getPrice()));
	}

	@Test
	@Transactional
	public void testMybatis2(){
		//1、假设数据库中已经存在name为aaa的数据
		//2、再插入一条name为aaa的数据，
		productMapper.insert("aaa", 12.3);

		//3、然后查找name为aaa的对象，则会查找到多个，因此会报错，且事务默认就回滚了，前提是需要加上事务（方法上或者类上）
		Product product = productMapper.findByName("aaa");

	}
}

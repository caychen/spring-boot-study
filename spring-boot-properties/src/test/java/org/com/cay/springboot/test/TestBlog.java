package org.com.cay.springboot.test;

import org.com.cay.springboot.entity.Blog;
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
public class TestBlog {

	//方法一：直接在Blog可以使用@Component注解直接注入到需要的地方，
	//方法二：也可以通过使用配置Config来生成一个Bean(使用@Bean注解，例如BlogConfig.java)
	@Autowired
	private Blog blog;

	@Test
	public void test(){
		System.out.println(blog);
	}
}

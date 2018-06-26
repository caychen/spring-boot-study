package org.com.cay.springboot.test;

import org.com.cay.springboot.config.ProfileConfig;
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
public class TestProfile {

	@Autowired
	private ProfileConfig config;

	@Test
	public void testProfile(){
		System.out.println(config.getUsername());
	}
}

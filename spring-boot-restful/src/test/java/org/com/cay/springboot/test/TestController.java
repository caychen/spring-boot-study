package org.com.cay.springboot.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by Cay on 2017/9/22.
 */
@SpringBootTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestController {

	@Autowired
	private WebApplicationContext webContext;

	private MockMvc mvc;

	@Before
	public void setup(){
		//可以用于测试所有控制器的web测试
		mvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void testUserController() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/user/").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"Cay\"}]")));
	}

	@Test
	public void testPersonController() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/person/"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(equalTo("person-list")));
	}



}

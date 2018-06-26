package org.com.cay.springboot.test;

import org.com.cay.springboot.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Cay on 2017/9/6.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class TestUserController {

	private MockMvc mvc;

	@Before
	public void init(){
		//只能用于单一控制器的web测试
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void testGetAllUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"Cay\"}]")));
	}

	@Test
	public void testPostUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/user/").param("id", "2").param("name", "Amy")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));
	}

	@Test
	public void testGetOneUser() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/user/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"id\":1,\"name\":\"Cay\"}")));
	}

	@Test
	public void testPutUser() throws Exception{
		mvc.perform(MockMvcRequestBuilders.put("/user/1").param("name", "Hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));
	}

	@Test
	public void testDeleteUser() throws Exception{
		mvc.perform(MockMvcRequestBuilders.delete("/user/1"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));
	}

}

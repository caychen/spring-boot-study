package org.com.cay.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Cay on 2017/9/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestJdbcTemplate {

	//指定使用哪个jdbcTemplate，或者可以直接使用@Resource(name="primaryJdbcTemplate")
	@Autowired
	@Qualifier(value="primaryJdbcTemplate")
	private JdbcTemplate primaryJdbcTemplate;

	//类似primaryJdbcTemplate
	@Autowired
	@Qualifier(value="secondaryJdbcTemplate")
	private JdbcTemplate secondaryJdbcTemplate;


	@Test
	public void testPrimaryJdbcTemplate(){
		Integer count1 = primaryJdbcTemplate.queryForObject("select count(*) as c from user", Integer.class);
		Integer count2 = secondaryJdbcTemplate.queryForObject("select count(*) as c from user", Integer.class);

		System.out.println(count1);
		System.out.println(count2);

		primaryJdbcTemplate.execute("insert into user values(null, 'Hello', 20)");
		secondaryJdbcTemplate.execute("insert into user values(null, 'Kitty', 19)");
	}
}

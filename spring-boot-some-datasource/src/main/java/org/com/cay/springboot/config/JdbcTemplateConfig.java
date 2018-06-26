package org.com.cay.springboot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Cay on 2017/9/25.
 */
@Configuration
public class JdbcTemplateConfig {

	@Bean(name="primaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(@Qualifier(value="primaryDataSource") DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}

	@Bean(name="secondaryJdbcTemplate")
	public JdbcTemplate secondaryJdbcTemplate(@Qualifier(value="secondaryDataSource")DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
}

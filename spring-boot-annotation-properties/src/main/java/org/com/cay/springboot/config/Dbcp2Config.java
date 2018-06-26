package org.com.cay.springboot.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * Created by Cay on 2018/4/26.
 */
//通过加载外部properties
@Configuration
@PropertySource("classpath:dbcp2.properties")//如果配置信息不在全局配置文件application.properties/yml中，则需要显式加载外部properties
public class Dbcp2Config {

	@Value("${dbcp2.driver}")
	private String driver;

	@Value("${dbcp2.url}")
	private String url;

	@Value("${dbcp2.username}")
	private String username;

	@Value("${dbcp2.password}")
	private String password;

//	@Primary
	@Bean("dbcp2")
	public DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		System.out.println("@Value ===================：" + driver);

		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}
}

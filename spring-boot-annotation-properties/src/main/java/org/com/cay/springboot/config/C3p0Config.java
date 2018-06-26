package org.com.cay.springboot.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by Cay on 2018/4/26.
 */

//通过Environment来获取配置属性
@Configuration
@PropertySource("classpath:c3p0.properties")//引入外部配置文件
public class C3p0Config {

	@Autowired
	private Environment env;

//	@Primary
	@Bean("c3p0")
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		System.out.println("env ==========：" + env.getProperty("c3p0.driver"));

		dataSource.setDriverClass(env.getProperty("c3p0.driver"));
		dataSource.setJdbcUrl(env.getProperty("c3p0.url"));
		dataSource.setUser(env.getProperty("c3p0.username"));
		dataSource.setPassword(env.getProperty("c3p0.password"));
		return dataSource;
	}
}

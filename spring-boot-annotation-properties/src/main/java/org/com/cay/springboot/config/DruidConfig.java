package org.com.cay.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.com.cay.springboot.properties.DruidProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Cay on 2018/4/27.
 */
@Configuration
@EnableConfigurationProperties//(DruidProperties.class)
public class DruidConfig {

	private DruidProperties properties;

	public DruidConfig(DruidProperties properties){
		this.properties = properties;
	}

	@Bean("druid")
	public DruidDataSource dataSource(){
		System.out.println("properties =====================：" + properties.getDriverClassName());

		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(properties.getDriverClassName());
		dataSource.setUrl(properties.getUrl());
		dataSource.setUsername(properties.getUsername());
		dataSource.setPassword(properties.getPassword());
		return dataSource;
	}
}

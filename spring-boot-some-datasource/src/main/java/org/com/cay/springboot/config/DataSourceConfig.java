package org.com.cay.springboot.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Cay on 2017/9/25.
 */
@Configuration
public class DataSourceConfig {

	@Primary
	@Bean(name = "primaryDataSource")
//	@Qualifier(value="primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.primary")//声明使用配置文件中前缀为spring.datasource.primary的数据源
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "secondaryDataSource")
//	@Qualifier(value="secondaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.secondary")//声明使用配置文件中前缀为spring.datasource.secondary的数据源
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}
}

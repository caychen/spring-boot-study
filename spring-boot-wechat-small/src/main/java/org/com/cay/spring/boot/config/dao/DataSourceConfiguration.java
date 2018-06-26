package org.com.cay.spring.boot.config.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by Cay on 2018/5/8.
 */
@Configuration
public class DataSourceConfiguration {

	@Bean
	@ConfigurationProperties(prefix = "c3p0")
	public DataSource dataSource(){
		return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
	}
}

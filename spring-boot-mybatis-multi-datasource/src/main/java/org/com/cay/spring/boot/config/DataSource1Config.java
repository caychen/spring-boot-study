package org.com.cay.spring.boot.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Cay on 2018/5/30.
 */
@Configuration
@MapperScan(basePackages = "org.com.cay.spring.boot.mapper1", sqlSessionTemplateRef = "sqlSessionTemplate1")
public class DataSource1Config {

	@Bean("dataSource1")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.db1")
	public DataSource dataSource1(){
		return DataSourceBuilder.create().build();
	}

	@Bean("sqlSessionFactory1")
	@Primary
	public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);

		//如果有mapper.xml映射文件夹
//		factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper1/*Mapper.xml"));
		return factory.getObject();
	}

	@Bean("dataSourceTransactionManager1")
	@Primary
	public DataSourceTransactionManager dataSourceTransactionManager1(@Qualifier("dataSource1") DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("sqlSessionTemplate1")
	@Primary
	public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}

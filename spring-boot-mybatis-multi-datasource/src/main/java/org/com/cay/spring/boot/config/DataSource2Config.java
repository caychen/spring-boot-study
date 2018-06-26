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
@MapperScan(basePackages = "org.com.cay.spring.boot.mapper2", sqlSessionTemplateRef = "sqlSessionTemplate2")
public class DataSource2Config {

	@Bean("dataSource2")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	public DataSource dataSource2(){
		return DataSourceBuilder.create().build();
	}

	@Bean("sqlSessionFactory2")
	public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);

		//如果有mapper.xml映射文件夹
//		factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper2/*Mapper.xml"));
		return factory.getObject();
	}

	@Bean("dataSourceTransactionManager2")
	public DataSourceTransactionManager dataSourceTransactionManager2(@Qualifier("dataSource2") DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("sqlSessionTemplate2")
	public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory){
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}

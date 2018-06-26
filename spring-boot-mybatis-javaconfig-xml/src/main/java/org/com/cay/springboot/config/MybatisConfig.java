package org.com.cay.springboot.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.management.MXBean;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Cay on 2017/12/19.
 */
@Configuration
//@MapperScan("org.com.cay.springboot.mapper")//mybatis接口所在的包
public class MybatisConfig {

	//使用默认的dataSource
	@Autowired
	private DataSource dataSource;

	//使用其他的dataSource，比如c3p0, dbcp, druid，需要依赖
//	@Bean
//	@ConfigurationProperties(prefix="spring.datasource")
//	public DataSource dataSource(){
//		return new DruidDataSource();
//	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

		//注入dataSource
		sqlSessionFactoryBean.setDataSource(dataSource);

		//添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));

		//
		sqlSessionFactoryBean.setTypeAliasesPackage("org.com.cay.springboot.vo");

		return sqlSessionFactoryBean.getObject();
	}

	//用于批量插入的SqlSession，在需要批量插入的service层注入该bean，使用getMapper的方法获取带有批量插入的Mapper，而不是使用直接注入的Mapper
	@Bean
	public SqlSession sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory(), ExecutorType.BATCH);
	}
}

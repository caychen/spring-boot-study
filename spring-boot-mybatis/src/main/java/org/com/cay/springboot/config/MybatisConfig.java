package org.com.cay.springboot.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Cay on 2018/4/4.
 */
@Configuration
public class MybatisConfig {

	//注册PageHelper的方法一
	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		pageHelper.setProperties(properties);
		return pageHelper;
	}

	@Bean
	public DataSource dataSource(){
		/**
		 * 在不导入任何第三方数据源依赖的情况下，默认DataSource加载的顺序
		 * "org.apache.tomcat.jdbc.pool.DataSource",
		 * "com.zaxxer.hikari.HikariDataSource",
		 * "org.apache.commons.dbcp.BasicDataSource", // deprecated
		 * "org.apache.commons.dbcp2.BasicDataSource"
		 */
		return DataSourceBuilder.create().build();
		//使用c3p0数据源
//		return DataSourceBuilder.create(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

		//实体别名包
		sqlSessionFactoryBean.setTypeAliasesPackage("org.com.cay.springboot.entity");

		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*Mapper.xml"));
		sqlSessionFactoryBean.setDataSource(dataSource());

		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSession sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory(), ExecutorType.BATCH);
	}
}

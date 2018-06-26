package org.com.cay.springboot.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Cay on 2018/3/14.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//配置get请求访问/login，则直接跳转到login.html页面
		registry.addViewController("/login").setViewName("login");
	}

	//配置org.springframework.security.web.session.HttpSessionEventPublisher监听器
	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> sessionListener(){
		return new ServletListenerRegistrationBean<>(sessionEventPublisher());
	}

	@Bean
	public HttpSessionEventPublisher sessionEventPublisher(){
		return new HttpSessionEventPublisher();
	}

}

package org.com.cay.springboot.config;

import org.com.cay.springboot.entity.Blog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Cay on 2017/9/27.
 */

@Configuration
public class BlogConfig {

	@Bean
	public Blog blog(){
		return new Blog();
	}

}

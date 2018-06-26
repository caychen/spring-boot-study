package org.com.cay.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.com.cay.spring.boot.dao")
public class SpringBootWechatSmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWechatSmallApplication.class, args);
	}
}

package org.com.cay.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//启用定时任务的配置
@EnableScheduling
@SpringBootApplication
public class SpringBootScheduledApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootScheduledApplication.class, args);
	}
}

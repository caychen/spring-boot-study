package org.com.cay.springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Cay on 2018/5/30.
 */
@Configuration
public class TopicConfig {

	@Bean
	public Queue topicQueue1() {
		return null;
	}
}

package org.com.cay.springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Cay on 2018/4/18.
 */
@Configuration
public class RabbitConfig {

	@Bean
	public Queue helloOne2OneQueue() {
		return new Queue("helloOne2One");
	}

	@Bean
	public Queue helloOne2ManyQueue() {
		return new Queue("helloOne2Many");
	}

	@Bean
	public Queue helloMany2ManyQueue() {
		return new Queue("helloMany2Many");
	}

	//@Bean
	public Queue helloObjectQueue() {
		return new Queue("helloObjectQueue");
	}
}

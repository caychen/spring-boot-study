package org.com.cay.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Cay on 2018/5/30.
 */
@Configuration
public class TopicConfig {

	public static final String TOPIC_QUEUE_NAME_1 = "topic-queue-1";
	public static final String TOPIC_QUEUE_NAME_2 = "topic-queue-2";
	public static final String TOPIC_EXCHANGE_NAME = "topic-exchange";

	public static final String ROUTE_KEY_1 = "topic.#";
	public static final String ROUTE_KEY_2 = "topic.*";

	@Bean
	public TopicExchange topicExchange() {
//        return new TopicExchange(TOPIC_EXCHANGE_NAME);//默认情况下，durable为true,auto-delete为false
		return (TopicExchange) ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_NAME).durable(true).build();
	}
	
	@Bean
	public Queue topicQueue1() {
		return new Queue(TOPIC_QUEUE_NAME_1);
	}

	@Bean
	public Queue topicQueue2() {
		return new Queue(TOPIC_QUEUE_NAME_2);
	}

	@Bean
	public Binding topicBinding1(TopicExchange topicExchange, Queue topicQueue1) {
		return BindingBuilder.bind(topicQueue1).to(topicExchange).with(ROUTE_KEY_1);
	}

	@Bean
	public Binding topicBinding2(TopicExchange topicExchange, Queue topicQueue2) {
		return BindingBuilder.bind(topicQueue2).to(topicExchange).with(ROUTE_KEY_2);
	}
}

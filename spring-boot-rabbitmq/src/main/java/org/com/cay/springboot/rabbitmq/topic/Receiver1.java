package org.com.cay.springboot.rabbitmq.topic;

import org.com.cay.springboot.config.TopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2018/4/18.
 */
@RabbitListener(queues = {TopicConfig.TOPIC_QUEUE_NAME_1})
@Component("topic-receiver1")
public class Receiver1 {

	@RabbitHandler
	public void handler(String content){
		System.out.println("Topic.Receiver1接收到：" + content);
	}
}

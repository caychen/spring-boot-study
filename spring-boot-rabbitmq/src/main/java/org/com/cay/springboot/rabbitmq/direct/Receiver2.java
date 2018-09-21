package org.com.cay.springboot.rabbitmq.direct;

import org.com.cay.springboot.config.DirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2018/4/18.
 */
@RabbitListener(queues = {DirectConfig.DIRECT_QUEUE_NAME_2})
@Component("direct-receiver2")
public class Receiver2 {

	@RabbitHandler
	public void handler(String content){
		System.out.println("Direct.Receiver2接收到：" + content);
	}
}

package org.com.cay.springboot.rabbitmq.hello.one2one;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2018/4/18.
 */
@RabbitListener(queues = {"helloOne2One"})
@Component
public class Receiver {

	@RabbitHandler
	public void handler(String content){
		System.out.println("接收到：" + content);
	}
}

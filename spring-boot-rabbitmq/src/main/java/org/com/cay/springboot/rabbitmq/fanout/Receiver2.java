package org.com.cay.springboot.rabbitmq.fanout;

import org.com.cay.springboot.config.FanoutConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2018/4/18.
 */
@RabbitListener(queues = {FanoutConfig.FANOUT_QUEUE_NAME_2})
@Component("fanout-receiver2")
public class Receiver2 {

	@RabbitHandler
	public void handler(String content){
		System.out.println("Fanout.Receiver2接收到：" + content);
	}
}

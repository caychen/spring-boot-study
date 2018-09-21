package org.com.cay.springboot.rabbitmq.fanout;

import org.com.cay.springboot.config.FanoutConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2018/4/18.
 */
@RabbitListener(queues = {FanoutConfig.FANOUT_QUEUE_NAME_1})
@Component("fanout-receiver1")
public class Receiver1 {

	@RabbitHandler
	public void handler(String content){
		System.out.println("Fanout.Receiver1接收到：" + content);
	}
}

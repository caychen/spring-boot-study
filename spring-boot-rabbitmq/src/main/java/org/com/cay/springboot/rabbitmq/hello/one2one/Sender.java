package org.com.cay.springboot.rabbitmq.hello.one2one;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Cay on 2018/4/18.
 */
@Component("one2one-sender")
public class Sender {

	@Autowired
	private Queue helloOne2OneQueue;

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void send(String name){
		String content = "hello : " + name + "，当前时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		amqpTemplate.convertAndSend(helloOne2OneQueue.getName(), content);
	}
}

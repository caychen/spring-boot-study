package org.com.cay.springboot.rabbitmq.hello.many2many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2018/4/18.
 */
@Component("many2many-sender2")
public class Sender2 {

	@Autowired
	private Queue helloMany2ManyQueue;

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void send(int i) {

		String content = "当前值为: " + i;
		amqpTemplate.convertAndSend(helloMany2ManyQueue.getName(), content);

	}
}

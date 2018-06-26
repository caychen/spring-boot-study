package org.com.cay.springboot.rabbitmq.hello.one2many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2018/4/18.
 */
@Component("one2many-sender")
public class Sender {

	@Autowired
	private Queue helloOne2ManyQueue;

	@Autowired
	private AmqpTemplate amqpTemplate;

	public void send() {

		for (int i = 0; i < 100; ++i) {
			String content = "当前值为: " + i;

			amqpTemplate.convertAndSend(helloOne2ManyQueue.getName(), content);
		}

	}
}

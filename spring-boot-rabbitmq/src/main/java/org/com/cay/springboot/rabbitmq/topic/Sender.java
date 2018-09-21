package org.com.cay.springboot.rabbitmq.topic;

import org.com.cay.springboot.config.TopicConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author:       Caychen
 * Date:         2018/9/14
 * Class:        org.com.cay.springboot.rabbitmq.topic.Sender
 * Version:      v1.0
 * Desc:
 */
@Component("topic-sender")
public class Sender {

    private static final String TOPIC_PREFIX = "topic.";

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String selector){
        String content = "hello，当前时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        amqpTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE_NAME, TOPIC_PREFIX + selector, content);
    }
}

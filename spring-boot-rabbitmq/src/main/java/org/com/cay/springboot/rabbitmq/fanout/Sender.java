package org.com.cay.springboot.rabbitmq.fanout;

import org.com.cay.springboot.config.FanoutConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author:       Caychen
 * Date:         2018/9/14
 * Class:        org.com.cay.springboot.rabbitmq.fanout.Sender
 * Version:      v1.0
 * Desc:
 */
@Component(value = "fanout-sender")
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String name){
        String content = "hello : " + name + "，当前时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        amqpTemplate.convertAndSend(FanoutConfig.FANOUT_EXCHANGE_NAME, "", content);
    }
}

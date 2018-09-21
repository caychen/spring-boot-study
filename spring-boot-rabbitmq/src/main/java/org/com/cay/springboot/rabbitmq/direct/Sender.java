package org.com.cay.springboot.rabbitmq.direct;

import org.com.cay.springboot.config.DirectConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author:       Caychen
 * Date:         2018/9/14
 * Class:        org.com.cay.springboot.rabbitmq.direct.Sender
 * Version:      v1.0
 * Desc:
 */
@Component("direct-sender")
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Integer selector) {
        String content = "hello，我是%d号，当前时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String routeKey = "";
        if (selector.intValue() == 1) {
            content = String.format(content, 1);
            routeKey = DirectConfig.ROUTE_KEY_1;
        } else if (selector.intValue() == 2) {
            content = String.format(content, 2);
            routeKey = DirectConfig.ROUTE_KEY_2;
        }
        amqpTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE_NAME, routeKey, content);
    }
}

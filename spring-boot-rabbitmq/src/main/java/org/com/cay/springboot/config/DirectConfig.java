package org.com.cay.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:       Caychen
 * Date:         2018/9/14
 * Class:        org.com.cay.springboot.config.DirectConfig
 * Version:      v1.0
 * Desc:
 */
@Configuration
public class DirectConfig {

    public static final String DIRECT_QUEUE_NAME_1 = "direct-queue-1";
    public static final String DIRECT_QUEUE_NAME_2 = "direct-queue-2";
    public static final String DIRECT_EXCHANGE_NAME = "direct-exchange";

    public static final String ROUTE_KEY_1 = "direct.route.key.1";
    public static final String ROUTE_KEY_2 = "direct.route.key.2";

    @Bean
    public Queue directQueue1() {
//        return new Queue(DIRECT_QUEUE_NAME_1);//默认情况，durable为true,exclusive为false,auto-delete为false
        return QueueBuilder.durable(DIRECT_QUEUE_NAME_1).build();
    }

    @Bean
    public Queue directQueue2() {
//        return new Queue(DIRECT_QUEUE_NAME_2);//默认情况，durable为true,exclusive为false,auto-delete为false
        return QueueBuilder.durable(DIRECT_QUEUE_NAME_2).build();
    }

    @Bean
    public DirectExchange directExchange() {
//        return new DirectExchange(DIRECT_EXCHANGE_NAME_1);//默认情况下，durable为true,auto-delete为false
        return (DirectExchange) ExchangeBuilder.directExchange(DIRECT_EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Binding directBinding1(DirectExchange directExchange, Queue directQueue1) {
        return BindingBuilder.bind(directQueue1).to(directExchange).with(ROUTE_KEY_1);
    }

    @Bean
    public Binding directBinding2(DirectExchange directExchange, Queue directQueue2) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with(ROUTE_KEY_2);
    }
}

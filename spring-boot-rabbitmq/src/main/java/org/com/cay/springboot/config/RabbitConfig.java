package org.com.cay.springboot.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Cay on 2018/4/18.
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Bean
    public Queue helloOne2OneQueue() {
        return new Queue("helloOne2One");
    }

    @Bean
    public Queue helloOne2ManyQueue() {
        return new Queue("helloOne2Many");
    }

    @Bean
    public Queue helloMany2ManyQueue() {
        return new Queue("helloMany2Many");
    }

    //@Bean
    public Queue helloObjectQueue() {
        return new Queue("helloObjectQueue");
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        //other code...
        cachingConnectionFactory.setPublisherConfirms(true);
        return cachingConnectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();

        //other code...

        //手动确认
        rabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return rabbitListenerContainerFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());

        //重点
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {

        });

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {

        });
        return rabbitTemplate;
    }

}


package org.com.cay.spring.boot.springbootkafka.producer;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.com.cay.spring.boot.springbootkafka.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.producer.KafkaMessageProducer
 * Date:         2019/4/28
 * Desc:
 */
@Component
@Slf4j
public class KafkaMessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendSingleMessage(String topic, String msg) {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", JSON.toJSONString(message));
        //topic.test为主题名
        kafkaTemplate.send(topic, JSON.toJSONString(message))
        //增加回调方法
        .addCallback((result) -> {
            //success
            log.info("send one message success...");
        }, (ex) -> {
            //exception
            log.error("send one message error...");
        });
    }

    public void sendBatchMessage(String topic) {
        List<Message> messages = Lists.newArrayList();

        Message message1 = new Message();
        message1.setId(System.currentTimeMillis());
        message1.setMsg(UUID.randomUUID().toString() + "$$$$$$$$$$$$");
        message1.setSendTime(new Date());
        messages.add(message1);

        Message message2 = new Message();
        message2.setId(System.currentTimeMillis());
        message2.setMsg(UUID.randomUUID().toString() + "############");
        message2.setSendTime(new Date());
        messages.add(message2);

        log.info("+++++++++++++++++++++  messages = {}", JSON.toJSONString(messages));
        //topic.test为主题名
        kafkaTemplate.send(topic, JSON.toJSONString(messages))
                //增加回调方法
                .addCallback((result) -> {
                    //success
                    log.info("send some messages success...");
                }, (ex) -> {
                    //exception
                    log.error("send some messages error...");
                });
    }

}

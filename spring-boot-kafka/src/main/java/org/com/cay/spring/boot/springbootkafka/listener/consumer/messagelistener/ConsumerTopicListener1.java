package org.com.cay.spring.boot.springbootkafka.listener.consumer.messagelistener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.listener.consumer.messagelistener.ConsumerTopicListener1
 * Date:         2019/4/29
 * Desc:        实现MessageListner接口的消费者监听类
 */
@Slf4j
public class ConsumerTopicListener1 implements MessageListener<String, Object> {

    @Override
    public void onMessage(ConsumerRecord<String, Object> data) {
        String topic = data.topic();
        String key = data.key();
        Object value = data.value();
        long offset = data.offset();
        int partition = data.partition();
        log.info("-------------topic:" + topic);
        log.info("-------------value:" + value);
        log.info("-------------key:" + key);
        log.info("-------------offset:" + offset);
        log.info("-------------partition:" + partition);
        log.info("~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");

    }
}

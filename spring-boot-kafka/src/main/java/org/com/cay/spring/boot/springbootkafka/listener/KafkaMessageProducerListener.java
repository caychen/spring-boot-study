package org.com.cay.spring.boot.springbootkafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.listener.KafkaMessageProducerListener
 * Date:         2019/4/29
 * Desc:        生产者监听器
 */
@Slf4j
public class KafkaMessageProducerListener implements ProducerListener {

    @Override
    public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
        log.info("==========kafka发送数据成功（日志开始）==========");
        log.info("----------topic:" + topic);
        log.info("----------partition:" + partition);
        log.info("----------key:" + key);
        log.info("----------value:" + value);
        log.info("----------RecordMetadata:" + recordMetadata);
        log.info("~~~~~~~~~~kafka发送数据成功（日志结束）~~~~~~~~~~");
    }

    @Override
    public void onError(String topic, Integer partition, Object key, Object value, Exception exception) {
        log.error("==========kafka发送数据错误（日志开始）==========");
        log.error("----------topic:" + topic);
        log.error("----------partition:" + partition);
        log.error("----------key:" + key);
        log.error("----------value:" + value);
        log.error("----------Exception:" + exception);
        log.error("~~~~~~~~~~kafka发送数据错误（日志结束）~~~~~~~~~~");
        exception.printStackTrace();
    }
}

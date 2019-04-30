package org.com.cay.spring.boot.springbootkafka.listener.consumer.kafkalistener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.listener.consumer.kafkalistener.ConsumerSingleTopicListener
 * Date:         2019/4/29
 * Desc:        消费者监听器(单条消费)
 */
@Component
@Slf4j
public class ConsumerSingleTopicListener {

    @KafkaListener(topics = {"topic.single"}, containerFactory = "singleContainerFactory")
    public void consumer(ConsumerRecord record) {
        Optional r = Optional.ofNullable(record);
        if (r.isPresent()) {
            Object message = r.get();
            log.info("单条消费 ### record: [{}]", record);
            log.info("单条消费 ### message: [{}]", message);
        }

    }
}

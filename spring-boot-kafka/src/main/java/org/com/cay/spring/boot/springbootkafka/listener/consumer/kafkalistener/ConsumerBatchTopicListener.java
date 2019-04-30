package org.com.cay.spring.boot.springbootkafka.listener.consumer.kafkalistener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.listener.ConsumerBatchTopicListener
 * Date:         2019/4/28
 * Desc:        消费者监听器(批量消费)
 */
@Component
@Slf4j
public class ConsumerBatchTopicListener {

    @KafkaListeners({
            @KafkaListener(topics = "topic.batch", containerFactory = "batchContainerFactory")
    })
    public void consumer(List<ConsumerRecord<?, ?>> records) {
        if (records != null && records.size() > 0) {
            records.parallelStream().forEach(record -> {
                Optional<?> kafkaMessage = Optional.ofNullable(record);
                if (kafkaMessage.isPresent()) {
                    Object message = kafkaMessage.get();
                    log.info("批量消费 &&&&&& record: [{}]", record);
                    log.info("批量消费 &&&&&& message: [{}]", message);
                }
            });
        }
    }
}

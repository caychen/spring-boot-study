package org.com.cay.spring.boot.springbootkafka.listener.consumer.kafkalistener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.listener.consumer.kafkalistener.ConsumerTopicPatternListener
 * Date:         2019/4/29
 * Desc:
 */
@Component
@Slf4j
public class ConsumerTopicPatternListener {

//    @KafkaListeners({
//            @KafkaListener(topicPattern = "topic.*", containerFactory = "batchContainerFactory")
//    })
    public void consumer(List<ConsumerRecord<?, ?>> records){
        if(records != null && records.size() > 0) {
            records.parallelStream().forEach(record -> {
                Optional<?> kafkaMessage = Optional.ofNullable(record);
                if (kafkaMessage.isPresent()) {
                    Object message = kafkaMessage.get();
                    log.info("正则匹配获取 record: [{}]", record);
                    log.info("正则匹配获取 message: [{}]", message);
                }
            });
        }
    }

}

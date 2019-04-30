package org.com.cay.spring.boot.springbootkafka.listener.consumer.kafkalistener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.listener.consumer.kafkalistener.ConsumerPartitionTopicListener
 * Date:         2019/4/30
 * Desc:
 */
@Slf4j
@Component
public class ConsumerPartitionTopicListener {

    @KafkaListener(containerFactory = "batchContainerFactory", topicPartitions = {
            @TopicPartition(topic = "topic-foo", partitions = {"0", "2"}),
            @TopicPartition(topic = "topic-bar", partitions = "1")
    })
    public void listen1(List<ConsumerRecord> records){
        records.stream().forEach(record -> {
            log.info("topic: [{}]", record.topic());
            log.info("partition: [{}]", record.partition());
            log.info("key: [{}]", record.key());
            log.info("value: [{}]", record.value());
        });
    }

    @KafkaListener(containerFactory = "batchContainerFactory", topicPartitions = {
            @TopicPartition(topic = "topic-foo", partitions = {"1"}),
            @TopicPartition(topic = "topic-bar", partitions = "0")
    })
    public void listen2(List<ConsumerRecord> records){
        records.stream().forEach(record -> {
            log.info("topic: [{}]", record.topic());
            log.info("partition: [{}]", record.partition());
            log.info("key: [{}]", record.key());
            log.info("value: [{}]", record.value());
        });
    }
}

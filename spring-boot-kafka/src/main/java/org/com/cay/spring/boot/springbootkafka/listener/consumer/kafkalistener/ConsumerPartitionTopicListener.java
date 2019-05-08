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

    //该监听了分区为0和2的topic-foo和分区为1号的topic-bar队列
    @KafkaListener(id = "listener1", containerFactory = "batchContainerFactory", topicPartitions = {
            @TopicPartition(topic = "topic-foo", partitions = {"0", "2"}),
            @TopicPartition(topic = "topic-bar", partitions = "1")
    })
    public void listen1(List<ConsumerRecord> records) {
        log.info("ConsumerPartitionTopicListener#listen1=======================");
        records.stream().forEach(record -> {
            log.info("topic: [{}]", record.topic());
            log.info("partition: [{}]", record.partition());
            log.info("key: [{}]", record.key());
            log.info("value: [{}]", record.value());
        });
    }

    //该监听了分区为1的topic-foo和分区为0号的topic-bar队列
    @KafkaListener(id = "listener2", containerFactory = "batchContainerFactory", topicPartitions = {
            @TopicPartition(topic = "topic-foo", partitions = {"1"}),
            @TopicPartition(topic = "topic-bar", partitions = "0")
    })
    public void listen2(List<ConsumerRecord> records) {
        log.info("ConsumerPartitionTopicListener#listen2########################");
        records.stream().forEach(record -> {
            log.info("topic: [{}]", record.topic());
            log.info("partition: [{}]", record.partition());
            log.info("key: [{}]", record.key());
            log.info("value: [{}]", record.value());
        });
    }
}

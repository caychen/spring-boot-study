package org.com.cay.spring.boot.springbootkafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.controller.KafkaPartitionController
 * Date:         2019/4/30
 * Desc:
 */
@Slf4j
@RestController
@RequestMapping("/kafka/partition")
public class KafkaPartitionController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/{topic}/{partition}/send")
    public String sendMessage(@PathVariable String topic, @PathVariable Integer partition) {
        String info = String.format("send message to [%s]:[%d]", topic, partition);

        kafkaTemplate.send(topic, partition, "date", "【分区发送】当前时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return info;
    }
}

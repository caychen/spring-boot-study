package org.com.cay.spring.boot.springbootkafka.task;

import org.com.cay.spring.boot.springbootkafka.producer.KafkaMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.task.KafkaSchedule
 * Date:         2019/4/28
 * Desc:
 */
@Component
public class KafkaSchedule {

    @Autowired
    private KafkaMessageProducer kafkaMessageProducer;

//    @Scheduled(cron = "0/10 * * * * ?")
    public void sendMessage(){
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        kafkaMessageProducer.sendSingleMessage("topic.single", "当前时间为：" + currentTime);
    }

}

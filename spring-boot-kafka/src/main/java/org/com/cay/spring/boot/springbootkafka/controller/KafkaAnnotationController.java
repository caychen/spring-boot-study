package org.com.cay.spring.boot.springbootkafka.controller;

import org.com.cay.spring.boot.springbootkafka.producer.KafkaMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.controller.KafkaController
 * Date:         2019/4/28
 * Desc:
 */
@RestController
@RequestMapping("/kafka/annotation")
public class KafkaAnnotationController {

    @Autowired
    private KafkaMessageProducer kafkaMessageProducer;

    @RequestMapping("/single")
    public String sendSingleMessage(String msg){
        kafkaMessageProducer.sendSingleMessage("topic.single", msg);
        return "single success";
    }

    @RequestMapping("/batch")
    public String sendBatchMessage(){
        kafkaMessageProducer.sendBatchMessage("topic.batch");
        return "batch success";
    }


}

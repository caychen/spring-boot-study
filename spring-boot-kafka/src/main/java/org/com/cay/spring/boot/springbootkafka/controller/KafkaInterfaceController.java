package org.com.cay.spring.boot.springbootkafka.controller;

import org.com.cay.spring.boot.springbootkafka.producer.KafkaMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.controller.KafkaInterfaceController
 * Date:         2019/4/29
 * Desc:
 */
@RestController
@RequestMapping("/kafka/interface")
public class KafkaInterfaceController {

    @Autowired
    private KafkaMessageProducer kafkaMessageProducer;

    @RequestMapping("/send")
    public String sendSingleMessage(String msg){
        kafkaMessageProducer.sendSingleMessage("topic-single", msg);
        return "single success";
    }
}

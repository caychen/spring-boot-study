package org.com.cay.springboot.controller;

import org.com.cay.springboot.rabbitmq.topic.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:       Caychen
 * Date:         2018/9/17
 * Class:        org.com.cay.springboot.controller.TopicController
 * Version:      v1.0
 * Desc:
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    @Qualifier("topic-sender")
    private Sender sender;

    @RequestMapping("/send")
    public String send(String routeKey){
        sender.send(routeKey);
        return "success";
    }
}

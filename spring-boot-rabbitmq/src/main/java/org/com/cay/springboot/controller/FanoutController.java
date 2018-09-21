package org.com.cay.springboot.controller;

import org.com.cay.springboot.rabbitmq.fanout.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:       Caychen
 * Date:         2018/9/14
 * Class:        org.com.cay.springboot.controller.FanoutController
 * Version:      v1.0
 * Desc:
 */
@RestController
public class FanoutController {

    @Autowired
    @Qualifier("fanout-sender")
    private Sender sender;

    @RequestMapping("/fanout")
    public String hello(String name){
        sender.send(name);
        return "success";
    }
}

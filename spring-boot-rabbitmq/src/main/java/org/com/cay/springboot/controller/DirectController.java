package org.com.cay.springboot.controller;

import org.com.cay.springboot.rabbitmq.direct.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:       Caychen
 * Date:         2018/9/14
 * Class:        org.com.cay.springboot.controller.DirectController
 * Version:      v1.0
 * Desc:
 */
@RestController
public class DirectController {
    private static final Logger logger = LoggerFactory.getLogger(DirectController.class);

    @Autowired
    @Qualifier("direct-sender")
    private Sender sender;

    @RequestMapping("/direct")
    public String hello(@RequestParam(defaultValue = "1") int selector){
        logger.info("参数selector：" + selector);
        sender.send(selector);
        return "success";
    }
}

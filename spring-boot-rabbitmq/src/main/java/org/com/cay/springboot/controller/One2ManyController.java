package org.com.cay.springboot.controller;

import org.com.cay.springboot.rabbitmq.hello.one2many.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cay on 2018/5/17.
 */
@RestController
public class One2ManyController {

	@Autowired
	@Qualifier("one2many-sender")
	private Sender sender;

	@RequestMapping("/one2many")
	public String hello(){
		sender.send();
		return "success";
	}
}

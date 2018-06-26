package org.com.cay.springboot.controller;

import org.com.cay.springboot.rabbitmq.hello.one2one.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cay on 2018/5/17.
 */
@RestController
public class One2OneController {

	@Autowired
	@Qualifier("one2one-sender")
	private Sender sender;

	@RequestMapping("/one2one")
	public String hello(String name){
		System.out.println("name: " + name);
		sender.send(name);

		return name;
	}
}

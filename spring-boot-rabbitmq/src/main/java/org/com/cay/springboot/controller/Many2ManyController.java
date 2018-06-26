package org.com.cay.springboot.controller;

import org.com.cay.springboot.rabbitmq.hello.many2many.Sender1;
import org.com.cay.springboot.rabbitmq.hello.many2many.Sender2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cay on 2018/5/17.
 */
@RestController
public class Many2ManyController {

	@Autowired
	@Qualifier("many2many-sender1")
	private Sender1 sender1;

	@Autowired
	@Qualifier("many2many-sender2")
	private Sender2 sender2;

	@RequestMapping("/many2many")
	public String hello(){
		for(int i = 0; i < 100; ++i){
			sender1.send(i);
			sender2.send(i);
		}
		return "success";
	}
}

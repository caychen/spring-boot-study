package org.com.cay.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Cay on 2017/10/23.
 */
@Controller
@RequestMapping("/")
public class HelloController {

	@ResponseBody
	@RequestMapping("/hello")
	public String hello(String name){
		return "hello " + name;
	}
}

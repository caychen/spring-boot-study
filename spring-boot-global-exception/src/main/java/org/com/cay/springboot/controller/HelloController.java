package org.com.cay.springboot.controller;

import org.com.cay.springboot.exception.ExceptionEx;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Cay on 2017/9/27.
 */
@Controller
public class HelloController {

	@ResponseBody
	@RequestMapping("/hello")
	public String hello() throws Exception {
		throw new Exception("发生错误");
	}

	@ResponseBody
	@RequestMapping("/json")
	public String json() throws ExceptionEx {
		throw new ExceptionEx("JSON请求发生错误！");
	}
}

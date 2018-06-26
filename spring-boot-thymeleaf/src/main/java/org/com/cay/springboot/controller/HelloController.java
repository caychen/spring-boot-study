package org.com.cay.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Cay on 2017/9/6.
 */

@Controller
public class HelloController {

	@RequestMapping("/")
	public String hello(Model model){
		model.addAttribute("url", "http://www.baidu.com");

		return "index";
	}
}

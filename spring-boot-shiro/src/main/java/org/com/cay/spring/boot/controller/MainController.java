package org.com.cay.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Cay on 2018/6/1.
 */
@Controller
public class MainController {

	@RequestMapping({"/", "/index"})
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String loginPage(){
		return "login";
	}

	@GetMapping("/main")
	public String main(){
		return "main";
	}
}

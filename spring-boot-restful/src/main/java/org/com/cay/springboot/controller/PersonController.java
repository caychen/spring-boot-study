package org.com.cay.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cay on 2017/9/22.
 */

@RestController
@RequestMapping("/person")
public class PersonController {

	@RequestMapping("/")
	public String list(){

		return "person-list";
	}
}

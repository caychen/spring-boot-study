package org.com.cay.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cay on 2017/9/6.
 */

@RestController
public class HelloController {

//	@Value("${a}")
//	private int a;
//
//	@Value("${b}")
//	private int b;
//
//	@Value("${c}")
//	private int c;

//	@Value("${d}")
//	private long d;
//
//	@Value("${e}")
//	private long e;

	@Value("${f}")
	private long f;

	@Value("${g}")
	private String g;

	@Value("${h}")
	private String h;

	@Value("${i}")
	private String i;

	@RequestMapping(value = "/")
	public String hello() {
		System.out.println(i);
		return "hello world";
	}
}

package org.com.cay.spring.boot.controller;

import org.com.cay.spring.boot.entity.User;
import org.com.cay.spring.boot.mapper1.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Cay on 2018/5/30.
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/")
	public List<User> getAll(){

		return userMapper.getAll();
	}

}

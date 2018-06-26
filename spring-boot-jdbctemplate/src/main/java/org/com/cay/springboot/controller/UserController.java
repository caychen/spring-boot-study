package org.com.cay.springboot.controller;

import org.com.cay.springboot.entity.User;
import org.com.cay.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Cay on 2017/9/7.
 */

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping("/")
	public String insertUser(User user){
		int count = userService.insertUser(user);
		if(count != 1){
			return "error";
		}else{
			return "success";
		}
	}

	@GetMapping("/{uid}")
	public User getUserById(@PathVariable Integer uid){
		User user = userService.getUserById(uid);

		return user;
	}

	@GetMapping("/")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

	@PutMapping("/{uid}")
	public String updateUserById(@PathVariable Integer uid, User user){
		return userService.updateUserById(uid, user) == 1 ? "success" : "error";
	}

	@DeleteMapping("/{uid}")
	public String deleteUserById(@PathVariable Integer uid){
		return userService.deleteUserById(uid) == 1 ? "success" : "error";
	}
}

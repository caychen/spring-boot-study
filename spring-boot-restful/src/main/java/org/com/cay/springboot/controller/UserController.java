package org.com.cay.springboot.controller;

import org.com.cay.springboot.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Cay on 2017/9/6.
 */

@RestController
@RequestMapping("/user")
public class UserController {

	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());
	static{
		users.put(1L, new User(1, "Cay"));
	}

	//获取所有的user对象
	@GetMapping("/")
	public List<User> getAllUsers(){
		return new ArrayList<User>(users.values());
	}

	//新增一个user对象
	@PostMapping("/")
	public String postUser(@ModelAttribute User user){
		users.put(user.getId(), user);
		return "success";
	}

	//获取指定id的user对象
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id){
		return users.get(id);
	}

	//修改指定id的user对象
	@PutMapping("/{id}")
	public String putUser(@PathVariable Long id, User user){
		User u = users.get(id);
		u.setName(user.getName());
		return "success";
	}

	//删除指定id的user对象
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id){
		users.remove(id);
		return "success";
	}
}

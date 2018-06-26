package org.com.cay.springboot.service.impl;

import org.com.cay.springboot.entity.User;
import org.com.cay.springboot.repository.IUserRepository;
import org.com.cay.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Cay on 2017/9/22.
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}

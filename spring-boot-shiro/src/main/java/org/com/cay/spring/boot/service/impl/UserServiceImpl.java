package org.com.cay.spring.boot.service.impl;

import org.com.cay.spring.boot.dao.IUserDao;
import org.com.cay.spring.boot.entity.User;
import org.com.cay.spring.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Cay on 2018/6/1.
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public User findByUsername(String name) {
		return userDao.findByUsername(name);
	}
}

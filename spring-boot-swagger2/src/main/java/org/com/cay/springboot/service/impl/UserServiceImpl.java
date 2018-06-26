package org.com.cay.springboot.service.impl;

import org.com.cay.springboot.dao.IUserDao;
import org.com.cay.springboot.entity.User;
import org.com.cay.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Cay on 2018/3/13.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User getById(Integer id) {
		return userDao.getById(id);
	}

	@Override
	public int updateUser(Integer id, User user) {
		return userDao.updateUser(id, user);
	}

	@Override
	public int deleteUser(Integer id) {
		return userDao.deleteUser(id);
	}

}

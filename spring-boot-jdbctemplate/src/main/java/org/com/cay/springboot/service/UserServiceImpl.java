package org.com.cay.springboot.service;

import org.com.cay.springboot.dao.IUserDao;
import org.com.cay.springboot.entity.User;
import org.com.cay.springboot.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cay on 2017/9/7.
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public int updateUserById(Integer uid, User user) {
		return userDao.updateUserById(uid, user);
	}

	@Override
	public int deleteUserById(Integer uid) {
		return userDao.deleteUserById(uid);
	}
}

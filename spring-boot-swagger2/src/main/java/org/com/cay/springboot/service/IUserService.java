package org.com.cay.springboot.service;

import org.com.cay.springboot.entity.User;

import java.util.List;

/**
 * Created by Cay on 2018/3/13.
 */
public interface IUserService {
	List<User> getUserList();

	int addUser(User user);

	User getById(Integer id);

	int updateUser(Integer id, User user);

	int deleteUser(Integer id);
}

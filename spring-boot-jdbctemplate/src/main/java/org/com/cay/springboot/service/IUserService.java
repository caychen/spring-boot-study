package org.com.cay.springboot.service;

import org.com.cay.springboot.entity.User;

import java.util.List;

/**
 * Created by Cay on 2017/9/7.
 */
public interface IUserService {

	int insertUser(User user);

	User getUserById(Integer id);

	List<User> getAllUsers();

	int updateUserById(Integer uid, User user);

	int deleteUserById(Integer uid);
}

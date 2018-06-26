package org.com.cay.springboot.dao;

import org.com.cay.springboot.entity.User;

import java.util.List;

/**
 * Created by Cay on 2018/4/23.
 */
public interface IUserDao {

	int insertUser(User user);

	User getUserById(Integer id);

	List<User> getAllUsers();

	int updateUserById(Integer uid, User user);

	int deleteUserById(Integer uid);
}

package org.com.cay.springboot.service;

import org.com.cay.springboot.entity.User;

import java.util.List;

/**
 * Created by Cay on 2017/9/22.
 */
public interface IUserService {

	List<User> getAllUsers();
}

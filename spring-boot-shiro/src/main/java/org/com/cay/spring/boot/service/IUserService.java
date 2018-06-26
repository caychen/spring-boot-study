package org.com.cay.spring.boot.service;

import org.com.cay.spring.boot.entity.User;

/**
 * Created by Cay on 2018/6/1.
 */
public interface IUserService{

	User findByUsername(String name);
}

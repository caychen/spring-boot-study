package org.com.cay.spring.boot.dao;

import org.com.cay.spring.boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Cay on 2018/6/1.
 */
public interface IUserDao extends JpaRepository<User, Long> {

	User findByUsername(String name);
}

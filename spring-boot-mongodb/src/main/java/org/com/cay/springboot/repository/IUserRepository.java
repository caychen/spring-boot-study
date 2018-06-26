package org.com.cay.springboot.repository;

import org.com.cay.springboot.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Cay on 2017/9/27.
 */
public interface IUserRepository extends MongoRepository<User, Integer> {

	public User findByUsername(String username);
}

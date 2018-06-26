package org.com.cay.springboot.repository;

import org.com.cay.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Cay on 2017/9/22.
 */
public interface IUserRepository extends JpaRepository<User, Integer>{
}

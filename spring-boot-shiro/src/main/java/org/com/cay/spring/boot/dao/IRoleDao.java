package org.com.cay.spring.boot.dao;

import org.com.cay.spring.boot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Cay on 2018/6/1.
 */
public interface IRoleDao extends JpaRepository<Role, Long> {
}

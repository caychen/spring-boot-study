package org.com.cay.springboot.repository;

import org.com.cay.springboot.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by Cay on 2018/3/14.
 */
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

	SysUser findByUsername(String username);

	@Modifying
	@Query("update SysUser set loginTime = ?1 where username = ?2")
	void updateSysUserLoginTime(Date date, String username);

	@Modifying
	@Query("update SysUser set logoutTime = ?1 where username = ?2")
	void updateSysUserLogoutTime(Date date, String username);

}

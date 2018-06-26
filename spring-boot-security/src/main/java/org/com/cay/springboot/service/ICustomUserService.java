package org.com.cay.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;

/**
 * Created by Cay on 2018/3/14.
 */
public interface ICustomUserService extends UserDetailsService{

	//记录登录时间
	public void updateSysUserLoginTime(Date date, String username);

	//记录登出时间
	public void updateSysUserLogoutTime(Date date, String username);
}

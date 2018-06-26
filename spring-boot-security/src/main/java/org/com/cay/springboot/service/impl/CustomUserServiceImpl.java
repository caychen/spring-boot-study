package org.com.cay.springboot.service.impl;

import org.com.cay.springboot.entity.SysUser;
import org.com.cay.springboot.repository.SysUserRepository;
import org.com.cay.springboot.service.ICustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Cay on 2018/3/14.
 */
@Service
@Transactional
public class CustomUserServiceImpl implements ICustomUserService {

	@Autowired
	private SysUserRepository sysUserRepository;

	//UserDetailsService
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = sysUserRepository.findByUsername(username);
		if(sysUser == null){
			throw new UsernameNotFoundException("用户名不存在！");
		}
		return sysUser;
	}

	//ICustomUserService
	@Override
	public void updateSysUserLoginTime(Date date, String username) {
		sysUserRepository.updateSysUserLoginTime(date, username);
	}

	@Override
	public void updateSysUserLogoutTime(Date date, String username) {
		sysUserRepository.updateSysUserLogoutTime(date, username);
	}
}

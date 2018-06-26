package org.com.cay.spring.boot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.com.cay.spring.boot.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Cay on 2018/6/1.
 */
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/login")
	public String loginIn(User user) {
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			subject.login(token);
		} catch (UnknownAccountException e1) {
			logger.error(e1.getMessage());
			e1.printStackTrace();
		} catch (LockedAccountException e2) {
			logger.error(e2.getMessage());
			e2.printStackTrace();
		}

		//用户认证通过
		return "redirect:/main";
	}

	@RequiresRoles("userInfo:del")
	@RequestMapping("/del")
	@ResponseBody
	public String del(){
		return "delete success";
	}

}

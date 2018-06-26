package org.com.cay.springboot.controller;

import org.com.cay.springboot.utils.Msg;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Cay on 2018/3/14.
 */
@Controller
public class HomeController {

	@RequestMapping("/index")
	public String index(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg", msg);
		return "index";
	}

	@RequestMapping("/admin")
	@PreAuthorize("authenticated and hasRole('ADMIN')")//Spring EL表达式：已经登录的，并且拥有ADMIN权限的用户
	public String admin(Model model){
		return "admin";
	}
}
package org.com.cay.springboot.handler;

import org.com.cay.springboot.service.ICustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Cay on 2018/3/15.
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Autowired
	private ICustomUserService customUserService;
	@Autowired
	private SessionRegistry sessionRegistry;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		//记录登出时间
		customUserService.updateSysUserLogoutTime(new Date(), ((UserDetails)authentication.getPrincipal()).getUsername());

		HttpSession session = request.getSession();
		session.removeAttribute("user");
		session.invalidate();

		//List<SessionInformation> allSessions = sessionRegistry.getAllSessions(authentication.getPrincipal(), false);


		response.sendRedirect("/login");//重新回到登录页面
	}
}

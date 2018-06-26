package org.com.cay.springboot.handler;

import org.com.cay.springboot.service.ICustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Cay on 2018/3/15.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private ICustomUserService customUserService;

	@Autowired
	private SessionRegistry sessionRegistry;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		System.out.println(sessionRegistry);

		List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
		authorities.stream().forEach(authority -> System.out.println(authority.getAuthority()));

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println("用户名：" + userDetails.getUsername());
		System.out.println("密码：" + userDetails.getPassword());

		String ip = request.getRemoteAddr();
		String uri = request.getRequestURI();
		System.out.println("IP地址：" + ip);
		System.out.println("uri：" + uri);

		//更新用户登录时间
		customUserService.updateSysUserLoginTime(new Date(), userDetails.getUsername());

		request.getSession().setAttribute("user", userDetails);

		//List<SessionInformation> allSessions = sessionRegistry.getAllSessions(authentication.getPrincipal(), false);

		//转发到/index
		request.getRequestDispatcher("/index").forward(request, response);
	}
}

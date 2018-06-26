package org.com.cay.springboot.config;

import org.com.cay.springboot.handler.CustomAuthenticationFailureHandler;
import org.com.cay.springboot.handler.CustomAuthenticationSuccessHandler;
import org.com.cay.springboot.handler.CustomLogoutSuccessHandler;
import org.com.cay.springboot.service.ICustomUserService;
import org.com.cay.springboot.service.impl.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * Created by Cay on 2018/3/14.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用Security注解，例如最常用的@PreAuthorize
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public ICustomUserService customUserService(){
		return new CustomUserServiceImpl();
	}

	@Bean
	public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler(){
		return new CustomAuthenticationSuccessHandler();
	}

	@Bean
	public CustomAuthenticationFailureHandler customAuthenticationFailureHandler(){
		return new CustomAuthenticationFailureHandler();
	}

	@Bean
	public CustomLogoutSuccessHandler customLogoutSuccessHandler(){
		return new CustomLogoutSuccessHandler();
	}

	@Bean
	public SessionRegistry sessionRegistry(){
		return new SessionRegistryImpl();
	}

	// Web层面的配置，一般用来配置无需安全检查的路径
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
	}

	// 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//在内存中模拟保存user对象
//		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN", "USER");
//		auth.inMemoryAuthentication().withUser("root").password("root").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("amy").password("amy").roles("USER");

		//使用UserDetailsService获取数据库用户对象，可以指定加密器
		auth.userDetailsService(customUserService());//.passwordEncoder(new BCryptPasswordEncoder());
	}

	// Request层面的配置
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/static/**").permitAll()
				.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginPage("/login")//GET请求，登录页面的url为/login，不能直接访问templates下的html文件(登录请求也是/login，但是是POST请求)
					.usernameParameter("username")//登录页面用户名使用name=username,默认即为username，所以可以不配该行
					.passwordParameter("password")//登录页面密码使用name=password,默认即为password，所以可以不配该行

					//.successForwardUrl("/index")//默认登录成功后跳转到/下，修改默认的登录成功后跳转的url
					.successHandler(customAuthenticationSuccessHandler())//可以在用户成功登录后获取用户的一些用户信息，或者获取用户登录时间点以便存于数据库

					//.failureUrl("/login?error")//认证失败后直接回到登录页面，并携带参数error
					.failureHandler(customAuthenticationFailureHandler())//认证失败后的处理，可以获取失败的原因
					.permitAll()//如果使用loginPage("xxx")配置自定义的登录url，则必须指定认证要求
				.and()
					.logout()
					//.logoutUrl("/logout")//触发注销操作的url，默认为/logout
					//.logoutSuccessUrl("/login")//注销操作发生后重定向到的url,默认为 /login?logout。如果指定了logoutSuccessHandler，则会忽略logoutSuccessUrl
					.logoutSuccessHandler(customLogoutSuccessHandler())//获取用户注销时间点存于数据库等功能
					.invalidateHttpSession(true)//注销后销毁session，默认为true
					.permitAll()
				.and()
					.httpBasic();
				//.and()

				//配置Session管理器，用于监听session，需要注意点：（据测试，第1点貌似不是必须的）
				//1、编写一个继承AbstractSecurityWebApplicationInitializer的子类，重写enableHttpSessionEventPublisher方法，使其返回true
				//2、使用ServletListenerRegistrationBean，注册一个Listener(在WebMvcConfig配置类里)
				//3、SpringSecurity是通过管理UserDetails对象来实现用户管理的，而类比较不能用==来比较，需要使用equals来比较，所以重写UserDetails子类的equals和hasCode方法（使用username作为唯一凭据）

				//用户每次登录都会调用ConcurrentSessionControlAuthenticationStrategy的onAuthentication方法，判断session是否有限制
				http
					.sessionManagement()//进行session管理
					.maximumSessions(1)//同一时刻，每个帐号只能有一个登录，默认为-1（无限制）
					.sessionRegistry(sessionRegistry())//用于Session管理，记录每一次的Session值
					.maxSessionsPreventsLogin(false)//true表示阻止后面的登录,false表示后面的登录会使前面登录成功的session失效，该值会影响ConcurrentSessionControlAuthenticationStrategy类的exceptionIfMaximumExceeded属性
						//假设将maxSessionsPreventsLogin设为true，则会存在一个问题，如果异常退出或者系统关闭，则下一次再登录的时候会导致登入不了
					.expiredUrl("/login");//session过期或者失效后回到指定url
	}
}

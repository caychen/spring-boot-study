package org.com.cay.spring.boot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.com.cay.spring.boot.realm.MyShiroRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Cay on 2018/6/1.
 */
@Configuration
public class ShiroConfig {

	private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		//设置realm
		securityManager.setRealm(myShiroRealm());

		return securityManager;
	}

	@Bean
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm realm = new MyShiroRealm();
		//设置密码匹配器
		realm.setCredentialsMatcher(hashedCredentialsMatcher());
		return realm;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(@Autowired SecurityManager securityManager){
		logger.info("ShiroConfig.shiroFilter()...");

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		//设置shiro安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		//设置过滤规则，注意顺序
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		//配置不被过滤的链接(匿名)
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/index", "anon");
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/login", "anon");

		//配置退出过滤器
		filterChainDefinitionMap.put("/logout", "logout");

		//过滤链定义：从上往下顺序判断，一般将/**放在最后

		//authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		filterChainDefinitionMap.put("/**", "authc");

		//设置登录url，如果不设置默认会自动寻找Web工程根目录下的"/login"页面
		shiroFilterFactoryBean.setLoginUrl("/login");

		//设置登录成功后的链接
		shiroFilterFactoryBean.setSuccessUrl("/main");

		//设置未授权界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");//加密算法
		hashedCredentialsMatcher.setHashIterations(5);//加密次数
		return hashedCredentialsMatcher;
	}

	/**
	 * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect(){
		return new ShiroDialect();
	}


	/**
	* @see org.apache.shiro.authz.annotation.RequiresAuthentication
	* @see org.apache.shiro.authz.annotation.RequiresUser
	* @see org.apache.shiro.authz.annotation.RequiresGuest
	* @see org.apache.shiro.authz.annotation.RequiresRoles
	* @see org.apache.shiro.authz.annotation.RequiresPermissions
	*/
	//加入注解的使用，不加入这个注解不生效
	@Bean
	public AuthorizationAttributeSourceAdvisor advisor(@Autowired SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return  advisor;
	}
}

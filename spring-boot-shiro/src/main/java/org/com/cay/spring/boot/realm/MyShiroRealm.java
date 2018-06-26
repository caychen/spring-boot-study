package org.com.cay.spring.boot.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.com.cay.spring.boot.entity.User;
import org.com.cay.spring.boot.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Cay on 2018/6/1.
 */
public class MyShiroRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	private IUserService userService;

	//用户角色和权限的认证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("权限认证：MyShiroRealm.doGetAuthorizationInfo()...");

		//此处获取的就是在doGetAuthenticationInfo()方法中的SimpleAuthenticationInfo()第一个参数，如果是用户名username，则返回的是username；而如果是实体类对象，则返回的就是实体类对象
		User user = (User)principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		user.getRoleList().stream().forEach(role -> {
			simpleAuthorizationInfo.addRole(role.getRole());

			role.getPermissions().stream().forEach(permission -> {
				simpleAuthorizationInfo.addStringPermission(permission.getPermission());
			});
		});

		return simpleAuthorizationInfo;
	}

	//用户认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("用户认证：MyShiroRealm.doGetAuthenticationInfo()...");
		//未登陆
		if(token.getPrincipal() == null) return null;

		//获取用户名信息
		String username = token.getPrincipal().toString();
		//查询数据库
		User user = userService.findByUsername(username);
		logger.info("User: {}", user);
		if(user == null){
			//用户未找到
			throw new UnknownAccountException("用户不存在...");
		}else if(user.getState() == 1){
			throw new LockedAccountException("用户被锁定...");
		}

		//1). principal: 认证的实体信息，可以是username, 也可以是数据表对应的用户的实体类对象
		Object principal = user;//此处使用实体类对象

		//2). credentials: 用户密码
		Object credentials = user.getPassword();

		//3). realmName: 当前realm对象的name，调用父类的getName()方法即可
		String realmName = getName();

		//4). credentialsSalt: 盐值加密
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());

		//找到该用户，开始认证
		AuthenticationInfo info = new SimpleAuthenticationInfo(
				principal,
				credentials,
				credentialsSalt,
				realmName);
		return info;
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "md5";
		String credentials = "123456";
		Object salt = ByteSource.Util.bytes("admin");
		int hashIterations = 5;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}
}

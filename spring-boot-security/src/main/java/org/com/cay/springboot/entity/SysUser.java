package org.com.cay.springboot.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Cay on 2018/3/14.
 */
@Entity
@Table
public class SysUser implements UserDetails {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique = true)
	private String username;
	private String password;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logoutTime;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private List<SysRole> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		this.getRoles().stream().forEach(role -> auths.add(new SimpleGrantedAuthority(role.getName())));
		return auths;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}


	//判断用户是否已经登录过，需要重写equals和hashCode方法
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SysUser sysUser = (SysUser) o;

		return username.equals(sysUser.username);
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}
}

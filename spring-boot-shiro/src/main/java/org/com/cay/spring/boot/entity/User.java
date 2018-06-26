package org.com.cay.spring.boot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Cay on 2018/6/1.
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uId;

	@Column(unique = true)
	private String username;//帐号

	private String name;//名称（昵称或者真实姓名，不同系统不同定义）

	private String password; //密码

	private String salt;//加密密码的盐

	private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定

	@ManyToMany(fetch = FetchType.EAGER)//立即从数据库中进行加载数据
	@JoinTable(name = "tb_user_role",
			joinColumns = {@JoinColumn(name = "uId")},
			inverseJoinColumns = {@JoinColumn(name = "rId")}
	)
	private List<Role> roleList;// 一个用户具有多个角色

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "User{" +
				"uId=" + uId +
				", username='" + username + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", state=" + state +
				", roleList=" + roleList +
				'}';
	}
}
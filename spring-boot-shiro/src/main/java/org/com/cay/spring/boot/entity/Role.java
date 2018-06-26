package org.com.cay.spring.boot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Cay on 2018/6/1.
 */
@Entity
@Table(name = "tb_role")
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rId; // 编号

	private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的

	private String description; // 角色描述,UI界面显示使用

	private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

	//角色 -- 权限关系：多对多关系;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_role_permission",
			joinColumns = {@JoinColumn(name = "rId")},
			inverseJoinColumns = {@JoinColumn(name = "pId")}
	)
	private List<Permission> permissions;

	// 用户 - 角色关系定义;
	@ManyToMany
	@JoinTable(name = "tb_user_role",
			joinColumns = {@JoinColumn(name = "rId")},
			inverseJoinColumns = {@JoinColumn(name = "uId")}
	)
	private List<User> users;// 一个角色对应多个用户

	public Integer getrId() {
		return rId;
	}

	public void setrId(Integer rId) {
		this.rId = rId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
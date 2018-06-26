package org.com.cay.spring.boot.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Cay on 2018/6/1.
 */
@Entity
@Table(name = "tb_permission")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pId;//主键

	private String name;//名称

	@Column(columnDefinition = "enum('menu','button')")
	private String resourceType;//资源类型，[menu|button]

	private String url;//资源路径

	private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view

	private Long parentId; //父编号

	private String parentIds; //父编号列表

	private Boolean available = Boolean.FALSE;

	@ManyToMany
	@JoinTable(name = "tb_role_permission",
			joinColumns = {@JoinColumn(name = "pId")},
			inverseJoinColumns = {@JoinColumn(name = "rId")}
	)
	private List<Role> roles;

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}

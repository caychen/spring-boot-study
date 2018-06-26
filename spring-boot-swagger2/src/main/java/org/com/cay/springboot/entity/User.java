package org.com.cay.springboot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Cay on 2018/3/13.
 */
@ApiModel(value = "用户对象User")
public class User implements Serializable {

	@ApiModelProperty(value = "用户id", name = "uid")
	private Integer uid;
	@ApiModelProperty(value = "用户名", name = "uname")
	private String uname;
	@ApiModelProperty(value = "用户年龄", name = "uage")
	private Integer uage;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getUage() {
		return uage;
	}

	public void setUage(Integer uage) {
		this.uage = uage;
	}

	public User(Integer uid, String uname, Integer uage) {
		this.uid = uid;
		this.uname = uname;
		this.uage = uage;
	}

	public User() {
	}
}

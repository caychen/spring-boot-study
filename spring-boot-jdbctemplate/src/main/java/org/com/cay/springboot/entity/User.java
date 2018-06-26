package org.com.cay.springboot.entity;

import java.io.Serializable;

/**
 * Created by Cay on 2017/9/7.
 */
public class User implements Serializable {

	private Integer uid;
	private String uname;
	private int uage;

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

	public int getUage() {
		return uage;
	}

	public void setUage(int uage) {
		this.uage = uage;
	}
}

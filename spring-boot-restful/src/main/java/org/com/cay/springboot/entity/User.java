package org.com.cay.springboot.entity;

import java.io.Serializable;

/**
 * Created by Cay on 2017/9/6.
 */
public class User implements Serializable {

	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User() {
	}

	public User(long id, String name) {
		this.id = id;
		this.name = name;
	}
}

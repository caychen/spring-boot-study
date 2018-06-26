package org.com.cay.springboot.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by Cay on 2017/9/27.
 */
public class User implements Serializable {

	@Id
	private Integer id;
	private String username;
	private int age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public User(Integer id, String username, int age) {
		this.id = id;
		this.username = username;
		this.age = age;
	}
}

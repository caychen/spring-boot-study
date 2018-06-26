package org.com.cay.springboot.entity;

import java.io.Serializable;

/**
 * Created by Cay on 2018/4/4.
 */
public class Person implements Serializable {
	private Integer id;
	private Integer age;
	private String username;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", age=" + age +
				", username='" + username + '\'' +
				'}';
	}
}

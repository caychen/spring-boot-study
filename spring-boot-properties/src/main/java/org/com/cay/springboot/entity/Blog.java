package org.com.cay.springboot.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2017/9/27.
 */

//@Component
@ConfigurationProperties(prefix = "com.blog")//通过前缀为com.blog的配置自动注入到该java bean中，属性名必须与配置文件中的属性对应
public class Blog{
	private String title;
	private String content;
	private long number;
	private int num1;
	private int num2;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	@Override
	public String toString() {
		return "Blog{" +
				"title='" + title + '\'' +
				", content='" + content + '\'' +
				", number=" + number +
				", num1=" + num1 +
				", num2=" + num2 +
				'}';
	}
}

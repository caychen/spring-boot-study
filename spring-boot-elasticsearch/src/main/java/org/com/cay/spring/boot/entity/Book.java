package org.com.cay.spring.boot.entity;

import io.searchbox.annotations.JestId;

/**
 * Created by Cay on 2018/5/30.
 */
public class Book {

	@JestId
	private Integer id;
	private String author;
	private String content;
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

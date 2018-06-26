package org.com.cay.spring.boot.controller;

import org.com.cay.spring.boot.entity.Book;
import org.com.cay.spring.boot.mapper2.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Cay on 2018/5/30.
 */
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookMapper bookMapper;

	@GetMapping("/")
	public List<Book> getAll(){

		return bookMapper.getAll();
	}

}

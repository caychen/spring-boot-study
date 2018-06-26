package org.com.cay.spring.boot.mapper2;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.com.cay.spring.boot.entity.Book;

import java.util.List;

/**
 * Created by Cay on 2018/5/30.
 */
public interface BookMapper {

	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "author", property = "author"),
			@Result(column = "title", property = "title"),
			@Result(column = "content", property = "content"),
	})
	@Select("SELECT * FROM book")
	List<Book> getAll();
}

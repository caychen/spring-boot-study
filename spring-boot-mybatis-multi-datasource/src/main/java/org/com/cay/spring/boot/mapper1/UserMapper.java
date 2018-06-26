package org.com.cay.spring.boot.mapper1;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.com.cay.spring.boot.entity.User;

import java.util.List;

/**
 * Created by Cay on 2018/5/30.
 */
public interface UserMapper {

	@Results({
			@Result(id = true, column = "id", property = "id"),
			@Result(column = "name", property = "name")
	})
	@Select("SELECT * FROM user")
	List<User> getAll();
}

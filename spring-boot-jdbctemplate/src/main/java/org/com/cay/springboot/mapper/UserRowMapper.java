package org.com.cay.springboot.mapper;

import org.com.cay.springboot.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Cay on 2017/9/7.
 */
public class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet resultSet, int i) throws SQLException {
		User user = new User();
		user.setUid(resultSet.getInt("uid"));
		user.setUname(resultSet.getString("uname"));
		user.setUage(resultSet.getInt("uage"));
		return user;
	}
}

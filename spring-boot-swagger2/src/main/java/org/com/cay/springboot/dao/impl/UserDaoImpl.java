package org.com.cay.springboot.dao.impl;

import org.com.cay.springboot.dao.IUserDao;
import org.com.cay.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Cay on 2018/3/13.
 */
@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<User> getUserList() {
		return jdbcTemplate.query("select * from user", (rs, i) -> {
					int uid = rs.getInt("uid");
					String uname = rs.getString("uname");
					int uage = rs.getInt("uage");
					return new User(uid, uname, uage);
				}
		);
	}

	@Override
	public int addUser(User user) {
		return jdbcTemplate.update("insert into user values(null, ?, ?)", user.getUname(), user.getUage());
	}

	@Override
	public int deleteUser(Integer id) {
		return jdbcTemplate.update("delete from user where uid = ?", id);
	}

	@Override
	public int updateUser(Integer id, User user) {
		return jdbcTemplate.update("update user set uname = ? where uid = ?", user.getUname(), id);
	}

	@Override
	public User getById(Integer id) {
		return jdbcTemplate.queryForObject("select * from user where uid = ?", (rs, i) -> {
			int uid = rs.getInt("uid");
			String uname = rs.getString("uname");
			int uage = rs.getInt("uage");
			return new User(uid, uname, uage);
		}, id);
	}


}

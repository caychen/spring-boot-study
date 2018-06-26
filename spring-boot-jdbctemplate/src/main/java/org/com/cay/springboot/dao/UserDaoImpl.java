package org.com.cay.springboot.dao;

import org.com.cay.springboot.entity.User;
import org.com.cay.springboot.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cay on 2018/4/23.
 */
@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insertUser(User user) {
		return jdbcTemplate.update("insert into user values(null, ?, ?)", user.getUname(), user.getUage());
	}

	@Override
	public User getUserById(Integer id) {
		Object[] params = {id};
		return jdbcTemplate.queryForObject("select * from user where uid=?", params, new UserRowMapper());
	}

	@Override
	public List<User> getAllUsers() {
		return jdbcTemplate.query("select * from user", new UserRowMapper());
	}

	@Override
	public int updateUserById(Integer uid, User user) {
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<>();
		boolean isUpdate = false;

		if (user != null) {
			sb.append("update user set ");

			if (user.getUname() != null && !"".equals(StringUtils.trimWhitespace(user.getUname()))) {
				isUpdate = true;
				sb.append(" uname = ?,");
				params.add(StringUtils.trimWhitespace(user.getUname()));
			}
			if (user.getUage() != 0) {
				isUpdate = true;
				sb.append(" uage = ?,");
				params.add(user.getUage());
			}
		}

		if (isUpdate) {
			sb.deleteCharAt(sb.length() - 1);

			sb.append(" where uid = ? ");
			params.add(user.getUid());
			return jdbcTemplate.update(sb.toString(), params.toArray());
		} else {
			return 0;
		}

	}

	@Override
	public int deleteUserById(Integer uid) {
		return jdbcTemplate.update("delete from user where uid = ?", uid);
	}
}

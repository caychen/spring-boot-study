package org.com.cay.spring.boot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.com.cay.spring.boot.vo.Area;

import java.util.List;

/**
 * Created by Cay on 2018/5/8.
 */
@Mapper
public interface AreaDao {

	List<Area> queryAllArea();

	Area queryById(Integer id);

	int insert(Area area);

	int update(Area area);

	int delete(Integer id);
}

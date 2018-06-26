package org.com.cay.spring.boot.service;

import org.com.cay.spring.boot.vo.Area;

import java.util.List;

/**
 * Created by Cay on 2018/5/9.
 */
public interface AreaService {

	List<Area> queryAllArea();

	Area queryById(Integer id);

	boolean insert(Area area);

	boolean update(Area area);

	boolean delete(Integer id);
}

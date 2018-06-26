package org.com.cay.spring.boot.dao;

import org.com.cay.spring.boot.vo.Area;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Cay on 2018/5/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {

	@Autowired
	private AreaDao areaDao;

	@Test
	@Ignore
	public void queryAllArea() throws Exception {
		List<Area> areas = areaDao.queryAllArea();
		assertEquals(2, areas.size());
	}

	@Test
	public void queryById() throws Exception {
		Area area = areaDao.queryById(1);
		System.out.println(area);
	}

	@Test
	@Ignore
	public void insert() throws Exception {
		Area area = new Area();
		area.setAreaName("南苑aaaaaaaaaaaaaaaa");
		area.setPriority(1);
		area.setCreateTime(new Date());
		int insert = areaDao.insert(area);
		assertEquals(1, insert);
	}

	@Test
	@Ignore
	public void update() throws Exception {
		Area area = new Area();
		area.setAreaId(4);
		area.setAreaName("北苑");
		area.setLastEditTime(new Date());
		int update = areaDao.update(area);
		assertEquals(1, update);
	}

	@Test
	public void delete() throws Exception {
		int delete = areaDao.delete(4);
		assertEquals(1, delete);
	}

}
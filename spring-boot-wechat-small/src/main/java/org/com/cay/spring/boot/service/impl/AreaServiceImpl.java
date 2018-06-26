package org.com.cay.spring.boot.service.impl;

import org.com.cay.spring.boot.dao.AreaDao;
import org.com.cay.spring.boot.vo.Area;
import org.com.cay.spring.boot.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Cay on 2018/5/9.
 */
@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;


	@Override
	public List<Area> queryAllArea() {
		return areaDao.queryAllArea();
	}

	@Override
	public Area queryById(Integer id) {
		return areaDao.queryById(id);
	}

	@Transactional
	@Override
	public boolean insert(Area area) {
		if (area != null && StringUtils.hasText(area.getAreaName())) {
			area.setCreateTime(new Date());
			area.setLastEditTime(new Date());
			try {
				int insert = areaDao.insert(area);
				if (insert > 0) {
					return true;
				} else {
					throw new RuntimeException("插入区域信息失败！");
				}
			} catch (Exception e) {
				throw new RuntimeException("插入区域信息失败：" + e.getMessage());
			}
		} else {
			throw new RuntimeException("区域信息不能为空！");
		}

	}

	@Transactional
	@Override
	public boolean update(Area area) {
		if (area != null && area.getAreaId() != null) {
			area.setLastEditTime(new Date());
			try {
				int update = areaDao.update(area);
				if (update > 0) {
					return true;
				} else {
					throw new RuntimeException("更新区域信息失败！");
				}
			} catch (Exception e) {
				throw new RuntimeException("更新区域信息失败：" + e.getMessage());
			}
		} else {
			throw new RuntimeException("区域信息id不能为空！");
		}
	}

	@Transactional
	@Override
	public boolean delete(Integer id) {
		if (id != null) {
			try {
				int delete = areaDao.delete(id);
				if (delete > 0) {
					return true;
				} else {
					throw new RuntimeException("删除区域信息失败！");
				}
			} catch (Exception e) {
				throw new RuntimeException("删除区域信息失败：" + e.getMessage());
			}
		} else {
			throw new RuntimeException("区域信息id不能为空！");
		}
	}
}

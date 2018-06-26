package org.com.cay.spring.boot.controller;

import org.com.cay.spring.boot.vo.Area;
import org.com.cay.spring.boot.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cay on 2018/5/9.
 */
@RestController
@RequestMapping("/admin")
public class AreaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AreaController.class);

	@Autowired
	private AreaService areaService;

	@GetMapping("/areas")
	public Map<String, Object> list() {
		LOGGER.info("查询area列表...");
		Map<String, Object> map = new HashMap<>();

		List<Area> areas = areaService.queryAllArea();
		map.put("areas", areas);
		return map;
	}

	@GetMapping("/area/{id}")
	public Map<String, Object> queryById(@PathVariable Integer id) {
		LOGGER.info("查询id为{}的area...", id);
		Map<String, Object> map = new HashMap<>();
		map.put("area", areaService.queryById(id));
		return map;
	}

	@PostMapping("/area")
	public Map<String, Object> insert(@RequestBody Area area) {
		LOGGER.info("新增area...");
		Map<String, Object> map = new HashMap<>();
		map.put("msg", areaService.insert(area));
		return map;
	}

	@PutMapping("/area")
	public Map<String, Object> update(@RequestBody Area area) {
		LOGGER.info("修改id为{}的area", area.getAreaId());
		Map<String, Object> map = new HashMap<>();
		map.put("msg", areaService.update(area));
		return map;
	}

	@DeleteMapping("/area/{id}")
	public Map<String, Object> delete(@PathVariable Integer id) {
		LOGGER.info("删除id为{}的area", id);
		Map<String, Object> map = new HashMap<>();
		map.put("msg", areaService.delete(id));
		return map;
	}

}

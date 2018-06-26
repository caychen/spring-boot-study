package org.com.cay.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.com.cay.dubbo.service.DubboService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cay on 2018/6/13.
 */
@RestController
public class DubboController {

	@Reference(version = "1.0", application = "${dubbo.application.id}", registry = "${dubbo.registry.id}")
	private DubboService dubboService;

	@RequestMapping("/test")
	public String getData(String name){
		return dubboService.getData(name);
	}
}

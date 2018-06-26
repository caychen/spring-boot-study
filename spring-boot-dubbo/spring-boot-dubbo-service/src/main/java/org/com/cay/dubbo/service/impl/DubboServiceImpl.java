package org.com.cay.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.com.cay.dubbo.service.DubboService;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2018/6/13.
 */
@Service(version = "1.0", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
@Component
public class DubboServiceImpl implements DubboService {

	@Override
	public String getData(String name) {
		return "Spring Boot：" + name;
	}
}

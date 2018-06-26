package org.com.cay.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2017/9/27.
 */
@Component
public class PropertiesConfig {

	@Value("${com.username}")
	private String username;

	@Value("${com.what}")
	private String what;

	@Value("${com.working}")
	private String working;

	public String getWorking() {
		return working;
	}

	public String getUsername() {
		return username;
	}

	public String getWhat() {
		return what;
	}

}

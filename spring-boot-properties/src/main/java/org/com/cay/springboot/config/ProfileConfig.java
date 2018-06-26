package org.com.cay.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Cay on 2017/9/27.
 */
@Component
public class ProfileConfig {

	@Value("${profiles.username}")
	private String username;

	public String getUsername() {
		return username;
	}

}

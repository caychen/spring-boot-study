package org.com.cay.springboot.initializer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Created by Cay on 2018/3/19.
 */
@Configuration
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	/**
	 * Override this if {@link HttpSessionEventPublisher} should be added as a listener.
	 * This should be true, if session management has specified a maximum number of
	 * sessions.
	 *
	 * @return true to add {@link HttpSessionEventPublisher}, else false
	 */
	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}

package org.com.cay.spring.boot.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cay on 2018/5/9.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String, Object> exceptionHandler(HttpServletRequest request, Exception e) {
		Map<String, Object> map = new HashMap<>();
		map.put("status", false);
		map.put("error", e.getMessage());
		return map;
	}
}

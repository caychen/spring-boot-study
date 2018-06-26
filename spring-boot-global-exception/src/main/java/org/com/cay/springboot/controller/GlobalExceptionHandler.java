package org.com.cay.springboot.controller;

import org.com.cay.springboot.exception.ErrorInfo;
import org.com.cay.springboot.exception.ExceptionEx;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Cay on 2017/9/27.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	//将错误信息返回到页面上
	@ExceptionHandler(value = Exception.class)//该方法用于处理Exception的错误
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("url", request.getRequestURL());
		mv.addObject("exception", e);
		mv.setViewName("error");
		return mv;
	}

	//返回json格式的错误信息
	@ResponseBody//需要添加@ResponseBody注解用于返回json
	@ExceptionHandler(value = ExceptionEx.class)//该方法用于处理ExceptionEx的错误
	public ErrorInfo<String> jsonErrorHandler(HttpServletRequest request, ExceptionEx e) {
		ErrorInfo<String> errorInfo = new ErrorInfo<>();

		errorInfo.setCode(ErrorInfo.ERROR);
		errorInfo.setMessage(e.getMessage());
		errorInfo.setData("Some problems occur...");
		errorInfo.setUrl(request.getRequestURL().toString());
		return errorInfo;
	}
}

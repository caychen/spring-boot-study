我们在做Web应用的时候，请求处理过程中发生错误是非常常见的情况。
Spring Boot提供了一个默认的映射：**/error**，当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用来展示异常内容。

### 1、创建全局异常处理类
* 使用 **@ControllerAdvice** 定义统一的异常处理类，而不是在每个Controller中逐个定义。
* **@ExceptionHandler** 用来定义函数针对的异常类型，最后将Exception对象和请求URL映射到指定的页面中
```java
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
```

### 2、可以在全局异常处理类中配置多种不同的异常Exception。
然后在 **@ControllerAdvice** 类中，根据抛出的具体Exception类型匹配 **@ExceptionHandler** 中配置的异常类型来匹配错误映射和处理。

### 3、返回JSON格式数据：
只需在 **@ExceptionHandler** 之后加入 **@ResponseBody**，就能让处理函数return的内容转换为JSON格式。
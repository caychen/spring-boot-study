### 1、引入AOP依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

### 2、在程序主类中增加@EnableAspectJAutoProxy来启用（可以不显示开启AOP）
因为在全局配置文件中，只要引入了AOP依赖后，spring.aop.auto属性默认是开启的，也就默认已经增加了 **@EnableAspectJAutoProxy** 注解。
```properties
# AOP
spring.aop.auto=true # Add @EnableAspectJAutoProxy.
spring.aop.proxy-target-class=false # Whether subclass-based (CGLIB) proxies are to be created (true) as opposed to standard Java interface-based proxies (false).
# 当我们需要使用CGLIB来实现AOP的时候，需要配置spring.aop.proxy-target-class=true，不然默认使用的是标准Java的实现。
```
	
### 3、实现Web切面：
* 使用 **@Aspect** 注解将一个java类定义为切面类
* 使用 **@Pointcut** 定义一个切入点，可以是一个规则表达式，
	* 根据需要在切入点不同位置的切入内容
	* 使用 **@Before** 在切入点开始处切入内容
	* 使用 **@After** 在切入点结尾处切入内容
	* 使用 **@AfterReturning** 在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
	* 使用 **@Around** 在切入点前后切入内容，并自己控制何时执行切入点自身的内容
	* 使用 **@AfterThrowing** 用来处理当切入内容部分抛出异常之后的处理逻辑

```java
@Aspect
@Component
public class WebLogAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ThreadLocal<Long> time = new ThreadLocal<>();
	
	@Pointcut("execution(public * org.com.cay.springboot.controller..*.*(..))")
	public void webLog() {
	}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
	
	    time.set(System.currentTimeMillis());
	
	    //获取请求
	    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	    HttpServletRequest request = attributes.getRequest();
	
	    //记录请求的信息
	    logger.info("URL: " + request.getRequestURL().toString());
	    logger.info("Method: " + request.getMethod());
	    logger.info("IP: " + request.getRemoteAddr());
	    logger.info("Class_Method: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
	    logger.info("Args: " + Arrays.toString(joinPoint.getArgs()));
	}
	
	@AfterReturning(returning = "result", pointcut = "webLog()")
	public void doReturn(Object result) {
	    logger.info("消耗时间：" + (System.currentTimeMillis() - time.get()));
	
	    logger.info("Result: " + result.toString());
}
}
```
本例中：
	通过@Pointcut注解定义切入点为org.com.cay.springboot.controller包下所有的函数，然后通过@Before获取对请求的详细信息，并记录请求的开始时间，最后通过@AfterReturning记录请求的结果，并计算请求消耗的时间长度

### 4、AOP优先级：
定义每个切面的优先级，我们需要 **@Order(i)** 注解来标识切面的优先级。i的值越小，优先级越高。


**总结：**
* 在切入点前的操作，按order的值由小到大执行
* 在切入点后的操作，按order的值由大到小执行

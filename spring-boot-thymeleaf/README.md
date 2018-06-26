### 1.Spring Boot提供了默认配置的模板引擎主要有以下几种：
* **Thymeleaf**:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
* **FreeMarker**:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```

* **Velocity**: SpringBoot1.5.x以上版本已经不支持Velocity
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-velocity</artifactId>
</dependency>
```

* **Groovy**:
```xml
<dependency>
    <groupId>org.codehaus.groovy</groupId>
    <artifactId>groovy-all</artifactId>
</dependency>
```

### 2.配置模版
```properties
#在开发测试阶段将thymeleaf或者freemarker的cache改为false
spring.thymeleaf.cache=false

#默认编码，可以不配
spring.thymeleaf.encoding=utf-8

#默认静态文件在classpath:/templates/目录下，可以不配
spring.thymeleaf.prefix=classpath:/templates/

spring.datasource.url=jdbc:mysql:///springboot
spring.datasource.username=root
spring.datasource.password=admin
```

### 3.默认的模板配置路径为：
> src/main/resources/templates
```html
<table border="1" cellspacing="0">
	<thead>
		<th>用户ID</th>
		<th>用户名</th>
		<th>年龄</th>
	</thead>
	<tbody>
		<tr th:each="user: ${users}">
			<td th:text="${user.uid}"></td>
			<td th:text="${user.uname}"></td>
			<td th:text="${user.uage}"></td>
		</tr>
	</tbody>
</table>
```
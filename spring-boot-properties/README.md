## 1、使用配置属性来实例化bean对象
1. 在bean对象的类上添加 **@ConfigurationProperties** 注解，将指定前缀的属性值注入到该bean对应的属性上，而且属性名必须与配置文件中的属性一一对应
2. 注入到需要的地方：
	* 方法一：可以直接在实体类上使用@Component注解直接声明为一个Component，直接由Spring管理
    * 方法二：也可以通过使用配置Config来生成一个Bean(使用@Bean注解，例如BlogConfig.java)

## 2、参数间的引用：
```properties
com.username=Cay
com.what=Hello world!
com.book=Springboot实战4.0
com.working=${com.username}正在学习${com.book} # com.working=Cay正在学习Springboot实战4.0
```

## 3、使用随机数：
使用 **random** ,例如：
```properties
#blog标题
com.blog.title=${random.value}

#blog内容
com.blog.content=${random.value}

#blog字数
com.blog.number=${random.long}

#blog作者
com.blog.author=Cay

#10以内的随机数
com.blog.num1=${random.int(10)}

#10-20以内的随机数
com.blog.num2=${random.int(10,20)}
```


## 4、通过命令行设置来设置属性值:
```
	java -jar xxx.jar --server.port=8888 #即通过命令行来设置port为8888
```

而为了应用的安全性，可以在Spring的启动类中将命令行设置配置属性的功能屏蔽:
```java
SpringApplication app = new SpringApplication();
app.setAddCommandLineProperties(false);
```

## 5、多环境配置
* 在Spring Boot中多环境配置文件名需要满足 **application-{profile}.properties** 的格式，其中 **{profile}** 对应你的环境标识，比如：
	* **application-dev.properties**：开发环境（dev）
	* **application-test.properties**：测试环境（test）
	* **application-prod.properties**：生产环境（prod）

至于哪个具体的配置文件会被加载，需要在 **application.properties** 文件中通过 **spring.profiles.active** 属性来设置，其值对应{profile}值。

* **总结：**
	* **在application.properties(yml)设置通用的配置属性，**
	* **在application-{profile}.properties中设置不同环境下的配置属性。**
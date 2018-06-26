### 1、banner设置：
	方法：可以在resources下新建一个banner.txt文件，内容为一串anscii的图形字符串。
	
[banner生成工具](http://patorjk.com/software/taag)

### 2、Spring Boot的主要优点：
	(1)、为所有Spring开发者更快的入门
	(2)、开箱即用，提供各种默认配置来简化项目配置
	(3)、内嵌式容器简化Web项目
	(4)、没有冗余代码生成和XML配置的要求

### 3、Spring Boot的基础结构共三个文件：
	src/main/java下的程序入口：xxxApplication
    src/main/resources下的配置文件：application.properties
    src/test/下的测试入口：xxxApplicationTests


* **spring-boot-starter**：核心模块，包括自动配置支持、日志和YAML
* **spring-boot-starter-test**：测试模块，包括JUnit、Hamcrest、Mockito
* **spring-boot-starter-web**：引入web模块


### 4、Spring Boot默认提供静态资源目录位置需置于classpath下
目录名需符合如下规则：
*	/static
*	/public
*	/resources
*	/META-INF/resources
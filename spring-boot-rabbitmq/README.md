## 1、安装
	（1）、安装Erlang [**Erlang下载地址**](http://www.erlang.org/downloads)
	（2）、安装RabbitMQ [**RabbitMQ下载地址**](https://www.rabbitmq.com/download.html)

## 2、浏览器管理
	访问http://localhost:15672/,使用guest/guest或者admin/admin登录
	
## 3、整合
## 3.1、引入RabbitMQ依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

### 3.2、添加RabbitMQ配置信息
```properties
spring.rabbitmq.host=ip地址
spring.rabbitmq.port=5672
spring.rabbitmq.username=用户名
spring.rabbitmq.password=密码
spring.rabbitmq.virtual-host=/
spring.rabbitmq.publisher-confirms=true
```
### 3.3、创建消息生产者
```java

```
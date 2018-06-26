## MongoDB简介

	1. MongoDB是一个基于分布式文件存储的数据库，它是一个介于关系数据库和非关系数据库之间的产品，其主要目标是在键/值存储方式（提供了高性能和高度伸缩性）和传统的RDBMS系统（具有丰富的功能）之间架起一座桥梁，它集两者的优势于一身。
	2. MongoDB支持的数据结构非常松散，是类似json的bson格式，因此可以存储比较复杂的数据类型，也因为他的存储格式也使得它所存储的数据在Nodejs程序应用中使用非常流畅。
	3. 既然称为NoSQL数据库，Mongo的查询语言非常强大，其语法有点类似于面向对象的查询语言，几乎可以实现类似关系数据库单表查询的绝大部分功能，而且还支持对数据建立索引。
	4. 但是，MongoDB也不是万能的，同MySQL等关系型数据库相比，它们在针对不同的数据类型和事务要求上都存在自己独特的优势。	在数据存储的选择中，坚持多样化原则，选择更好更经济的方式，而不是自上而下的统一化。

用处：  

	1. 我们可以直接用MongoDB来存储键值对类型的数据，如：验证码、Session等；
	2. 由于MongoDB的横向扩展能力，也可以用来存储数据规模会在未来变的非常巨大的数据，如：日志、评论等；
	3. 由于MongoDB存储数据的弱类型，也可以用来存储一些多变json数据，如：与外系统交互时经常变化的JSON报文。
	4. 而对于一些对数据有复杂的高事务性要求的操作，如：账户交易等就不适合使用MongoDB来存储。

## Mongodb官网：[https://www.mongodb.org/](https://www.mongodb.org/)

## 1、引入Mongodb依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

## 2、如果只是连接本地的mongodb，则不需要任何参数配置则可以直接连接本地的mongodb进行数据操作

## 3、Mongodb在JpaReponsitory的接口的基础上使用了MongoRepository接口，方法形式同JPA

## 4、远程Mongodb配置，需要在application.properties加入mongodb服务端的相关配置
```properties
# Mongodb3.x, 需要在mongo中对test库创建具备读写权限的用户（用户名为name，密码为pass）
spring.data.mongodb.uri=mongodb://name:pass@localhost:27017/test

#Mongodb 2.x，3.x配置不支持2.x配置
spring.data.mongodb.host=
spring.data.mongodb.port=27017 # 默认为27017
```

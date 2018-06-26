## SpringBoot1.4开始使用log4j2，不再支持log4j。

## 1、引入log4j2依赖
**注意点：在创建Spring Boot工程时，我们引入了spring-boot-starter/spring-boot-starter-web，其中包含了spring-boot-starter-logging，该依赖内容就是Spring Boot默认的日志框架Logback，所以我们在引入log4j2之前，需要先排除该包的依赖，再引入log4j2的依赖**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

## 2、配置log4j2的配置文件log4j2.xml
log4j2使用xml格式的配置文件，所以在src/main/resources/下新建log4j2.xml，加入对应的配置信息
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT" follow="true">
            <PatternLayout>
                <pattern>%d %p %C{1.} [%t] %m%n</pattern>
            </PatternLayout>
        </Console>

    </Appenders>
    <Loggers>
        <!-- 输出info级别信息 -->
        <Root level="info">
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```

## 3、在application.properties全局配置文件中配置log4j2.xml的路径：
```properties
logging.config=classpath:config/log4j2.xml
```
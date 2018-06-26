## 1、多数据源配置：
创建一个Spring配置类，定义两个 **DataSource** 用来读取 **application.properties** 中的不同配置。
假设：
* 主数据源配置为 **spring.datasource.primary** 开头的配置，
* 第二数据源配置为 **spring.datasource.secondary** 开头的配置
```java
@Configuration
public class DataSourceConfig {
    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
```

对应的 **application.properties** 配置如下：
```properties
spring.datasource.primary.url=jdbc:mysql://localhost:3306/test1
spring.datasource.primary.username=root
spring.datasource.primary.password=root
spring.datasource.primary.driver-class-name=com.mysql.jdbc.Driver

spring.datasource.secondary.url=jdbc:mysql://localhost:3306/test2
spring.datasource.secondary.username=root
spring.datasource.secondary.password=root
spring.datasource.secondary.driver-class-name=com.mysql.jdbc.Driver
```

## 2、使用JdbcTemplate：
创建两个 **JdbcTemplate**，分别注入 **primaryDataSource** 和 **secondaryDataSource** 的数据源来区分不同的 **JdbcTemplate**。
```java
@Bean(name = "primaryJdbcTemplate")
public JdbcTemplate primaryJdbcTemplate(
        @Qualifier("primaryDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
}

@Bean(name = "secondaryJdbcTemplate")
public JdbcTemplate secondaryJdbcTemplate(
        @Qualifier("secondaryDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
}
```

## 3、使用不同的JdbcTemplate：
指定注入不同的 **JdbcTemplate** 执行sql
```java
@Autowired
@Qualifier("primaryJdbcTemplate")
protected JdbcTemplate jdbcTemplate1;

@Autowired
@Qualifier("secondaryJdbcTemplate")
protected JdbcTemplate jdbcTemplate2;
```
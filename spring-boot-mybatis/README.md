## 1、添加mybatis所需的依赖
```xml
<!-- mybatis-spring-boot-starter包括spring-boot-starter-jdbc -->
<dependency>
	<groupId>org.mybatis.spring.boot</groupId>
	<artifactId>mybatis-spring-boot-starter</artifactId>
	<version>${mybatis-spring-boot-version}</version>
</dependency>
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<scope>runtime</scope>
</dependency>
```

## 2、添加数据源配置
```properties
spring.datasource.url=jdbc:mysql:///springboot
spring.datasource.username=root
spring.datasource.password=admin
```

## 3、开始编码
### 3.1、创建实体类，Product.java：
```java
public class Product implements Serializable {

	private Long id;
	private String name;
	private Double price;

	//省略getter和setter
}
```

### 3.2、创建Product数据访问接口IProductMapper.java：
```java
//@Mapper//如果@Mapper不添加，则需要在主程序入口上添加@MapperScan(value="接口所在的包全路径")用于接口被扫描到
public interface IProductMapper {

	Product findByName(@Param("name") String name);

	int insert(@Param("name") String name, @Param("price") Double price);

	int deleteAll();
}
```

### 3.3、创建IPoductMapper接口的映射配置IProductMapper.xml，映射文件放在src/main/resources/目录下
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="org.com.cay.springboot.mapper.IProductMapper">

    <select id="findByName" parameterType="string" resultType="product">
      select * from product where name = #{name}
    </select>

	<!-- 用于返回mysql主键 -->
    <insert id="insert" useGeneratedKeys="true" keyColumn="id" keyProperty="id" databaseId="mysql">
        insert into product(name, price) values(#{name}, #{price})
    </insert>

    <delete id="deleteAll">
      delete from product
    </delete>
</mapper>
```
### 3.4、添加mybatis配置信息
```properties
#指定mapper映射文件所在路径
mybatis.mapper-locations=classpath:mapper/*.xml
#实体别名包
mybatis.type-aliases-package=org.com.cay.springboot.entity
```

### 3.5、主类或者测试类
```java
//推荐使用@MapperScan
@MapperScan(basePackages = {"org.com.cay.springboot.mapper"})//可以扫描多个包
// 主类
@SpringBootApplication
// 测试类
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

@Autowired
private IProductMapper productMapper;

//代码省略...
```

## 4、@Mapper和@MapperScan的区别
* **@Mapper**：只能使当前添加注解的类被扫描到，如果应用实体多，则需要每个mapper类都需要使用该注解，比较麻烦。
* **@MapperScan**：可以一次性指定要扫描的Mapper类的包全路径，也可以指定多个。

## 5、使用注解
* **@Select**：查询注解
* **@Insert**：新增注解
* **@Update**：更新注解
* **@Delete**：删除注解
* **@Results**：结果集映射，value对应多个@Result
* **@Result**：结果集中各个字段映射
* **@ResultMap**：引用结果集，被引用的结果集@Results需要有id属性，然后@ResultMap使用id值
```java
// Person实体类
public class Person implements Serializable {
	private Integer id;
	private Integer age;
	private String username;//此处故意写成跟数据库中的字段不一致

	//省略getter和setter

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", age=" + age +
				", username='" + username + '\'' +
				'}';
	}
}
```

```java
// Person接口Mapper
public interface IPersonMapper {

	@Results(id = "PersonResultMap", value =
			{
					@Result(column = "id", property = "id"),
					@Result(column = "age", property = "age"),
					@Result(column = "name", property = "username")
			}
	)
	@Select("select id, age, name from person")
	List<Person> getAll();

	@ResultMap("PersonResultMap")//引用上面的@Results
	@Select("select id, age, name from person where name like #{name}")
	List<Person> getByNameLike(String name);

	//用于插入数据后返回主键，适合于mysql
	@Options(useGeneratedKeys=true, keyColumn = "id", keyProperty = "id")
	@Insert("insert into person values(null, #{age}, #{username})")
	void addPerson(Person person);

	@Update("update person p set p.age = #{age} where p.id = #{id}")
	void updatePerson(Person person);

	@Delete("delete from person where age < #{age}")
	void deletePerson(Integer age);
}
```

## 6、mybatis分页插件PageHelper
### 6.1、引入PageHelper分页插件依赖
```xml
<!-- mybatis分页插件 -->
<dependency>
	<groupId>com.github.pagehelper</groupId>
	<artifactId>pagehelper-spring-boot-starter</artifactId>
	<version>${pagehelper.version}</version>
</dependency>
```

### 6.2、配置pagehelper属性
* 方法一：在application.yml中配置pagehelper  
> mybatis.configuration-properties.offsetAsPageNum=true
> mybatis.configuration-properties.rowBoundsWithCount=true
> mybatis.configuration-properties.reasonable=true

* 方法二：新建一个类，使用 **@Configuration** 注解表示该类为一个配置类
```java
@Configuration
public class MybatisConfig {

	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}
```
### 6.3、使用pagehelper
在需要分页的方法中使用 **PageHelper.startPage(int pageNum,int pageSize)**;
例如：
```java
PageHelper.startPage(2, 2);
```

<br/>  

------ 
#### 插入一波广告，欢迎关注
|**#**|**#**|
|:--|:--:|
|**作者:**|**Cay**|
|**QQ:**|<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=412425870&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:412425870:41" alt="点击这里给我发消息" title="412425870"/></a>|
|**邮箱:**|**412425870@qq.com**|
|**csdn博客：**|**[http://blog.csdn.net/caychen](http://blog.csdn.net/caychen "我的csdn博客")**|
|**码云：**|**[https://gitee.com/caychen/](https://gitee.com/caychen/ "我的码云")**|
|**github：**|**[https://github.com/caychen](https://gitee.com/caychen/ "我的github")**|
|**点击群号或者扫描二维码即可加入QQ群:[328243383(1群)](https://jq.qq.com/?_wv=1027&k=54r3suD)**|**![](https://github.com/caychen/readme/raw/master/img/1%E7%BE%A4.png)**|
|**点击群号或者扫描二维码即可加入QQ群:[180479701(2群)](https://jq.qq.com/?_wv=1027&k=521g7zY)**|**![](https://github.com/caychen/readme/raw/master/img/2%E7%BE%A4.png)**|
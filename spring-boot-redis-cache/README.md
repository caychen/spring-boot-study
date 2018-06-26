## 1、添加Redis依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-redis</artifactId>
</dependency>
```

## 2、添加Redis配置
```properties
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.pool.max-idle=8
spring.redis.pool.min-idle=0
spring.redis.pool.max-active=8
spring.redis.pool.max-wait=-1
```

## 3、使用CacheManager
此时CacheManager即为**RedisCacheManager**
```java
@CacheConfig(cacheNames = "person")
// 如果在@Cacheable中添加cacheNames或者value属性，则可以省略该注解；
// 如果在@Cacheable中未添加cacheNames或者value属性，则必须在@CacheConfig中指定cacheNames或者value属性
public interface IPersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {

	@Cacheable(key = "#p0")//#p0表示函数的第一个参数
	Person findPersonByName(String name);
}
```

```java
// 测试代码
public void test(){
	System.out.println(cacheManager.getClass().getName());//RedisCacheManager

	//第一次查询的时候去数据库中查找name为Cay的Person对象，查询到的结果放入redis缓存中
	Person p1 = personService.findPersonByName("Cay");
	System.out.println("第一次查询：" + p1.getAge());

	//第二次查询的时候直接从redis缓存中获取
	Person p2 = personService.findPersonByName("Cay");
	System.out.println("第二次查询：" + p2.getAge());

	Random r = new Random(new Date().getTime());
	p2.setAge(r.nextInt(100));// 设置新年龄
	personService.save(p2);//只会更新数据库中的数据，而不会更新redis缓存中的数据

	//第三次查询依然还会去redis缓存中查询，而不是从数据库中查询，造成数据的不一致性。因为Redis的缓存是独立于应用的，无法在更新数据库之后去通知redis也更新缓存数据，而ehcache是进程间的缓存框架，当更新数据之后，也会同时更新ehcache缓存中的数据。
	Person p3 = personService.findPersonByName("Cay");
	System.out.println("第三次查询：" + p3.getAge());

}
```

## 4、造成Redis缓存数据不同步的原因：（Redis缓存与Ehcache缓存的区别）
* EhCache是 **进程内的缓存框架**：
	* 在第一次查询后，结果被加入到Ehcache缓存中，当第二次再次查询相同的数据时，直接从缓存中获取；
		而当去更新该条数据的同时，Ehcache会同时更新缓存中的缓存对象。
		
* Redis缓存是 **独立于应用程序的**，会造成如下问题：
	* 在第一次查询后，结果同样也会加到Redis缓存中，第二次查询同样的数据，直接从Redis缓存中获取；
		而当更新该条数据后，没有通知Redis去更新缓存中的数据，所以当再查询相同的数据后，会从Redis缓存中获取到旧数据，而不是新数据，会造成数据与数据库数据不一致。

## 5、解决Redis缓存同步问题
重写数据访问接口的save函数，并在其上面添加 **@CachePut(key = "#p0.name")**
> 表示当调用save函数后，会同时更新Cache缓存中对应person.name作为key的值，因为在findPersonByName()以name作为缓存的键key
```java
@CacheConfig(cacheNames = "person")
// 如果在@Cacheable中添加cacheNames或者value属性，则可以省略该注解；
// 如果在@Cacheable中未添加cacheNames或者value属性，则必须在@CacheConfig中指定cacheNames或者value属性
public interface IPersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {

	@Cacheable(key = "#p0")//#p0表示函数的第一个参数
	Person findPersonByName(String name);

	@Override
	@CachePut(key = "#p0.name")
	Person save(Person person);
}
```

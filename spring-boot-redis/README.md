# Redis是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库。

## 1、引入依赖：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-redis</artifactId>
</dependency>
```

## 2、Redis参数配置：
```properties
# REDIS (RedisProperties)
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=localhost
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.pool.max-active=8
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=-1
# 连接池中的最大空闲连接
spring.redis.pool.max-idle=8
# 连接池中的最小空闲连接
spring.redis.pool.min-idle=0
# 连接超时时间（毫秒）
spring.redis.timeout=0
```

## 3、在Redis中存储对象，以User对象为例：
### 3.1、创建User对象User.class:
```java
public class User implements Serializable {

	private String username;
	private int age;

	//省略getter和setter
}

```

### 3.2、需要自己实现RedisSerializer<T>接口来对传入对象进行序列化和反序列化
```java
public class RedisObjectSerializer implements RedisSerializer<Object> {
	private Converter<Object, byte[]> serializer = new SerializingConverter();
	private Converter<byte[], Object> deserializer = new DeserializingConverter();

	static final byte[] EMPTY_ARRAY = new byte[0];

	public Object deserialize(byte[] bytes) {
	    if (isEmpty(bytes)) {
	        return null;
	    }

	    try {
	      return deserializer.convert(bytes);
	    } catch (Exception ex) {
	      throw new SerializationException("Cannot deserialize", ex);
	    }
	}

	public byte[] serialize(Object object) {
	    if (object == null) {
	        return EMPTY_ARRAY;
	    }
	
	    try {
	        return serializer.convert(object);
	    } catch (Exception ex) {
	        return EMPTY_ARRAY;
	    }
	}

    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
}
```

### 3.3、创建存储对象的RedisTemplate：
```java
@Configuration
public class RedisConfig {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, User> template = new RedisTemplate<String, User>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
```
        
### 3.4、使用注解注入RedisTemplate进行存储
```java
@Autowired
private RedisTemplate<String, User> redisTemplate;
```
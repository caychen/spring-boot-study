一、JPA

	Spring Data JPA 是Spring Data 的一个子项目，它通过提供基于JPA的Repository极大了减少了操作JPA的代码。Spring Data JPA旨在通过减少实际需要的数量来显着提高数据访问层的实现。

	在Spring环境中需要配置大量的XML配置，但是SpringBoot基本上帮助我们配置好了，我们只需要简单地配置一下DataSource和几项jpa额外的配置就完成了整个配置持久化层的工作。EntityManagerFactory 那些都不用配置了。

	JPA不是一种框架，而只是一种ORM规范，而这种规范具体由其他厂商来实现，其中最熟悉的莫过于Hibernate了。

	网络上对JPA的褒贬不一，特别是用JPA进行多表操作的时候，确实是比较繁琐。当然任何技术有好的方面也有坏的方面，本章重点不在于此。



二、添加依赖

    <!-- jpa依赖 -->
    <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <!-- mysql依赖 -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>



三、数据源和JPA配置

    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.url=jdbc:mysql:///springboot
    spring.datasource.username=root
    spring.datasource.password=admin
    
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true



四、源码分析

在Spring Boot自动配置的时候，一旦引入spring-boot-starter-data-jpa，就会完成JpaRepositoriesAutoConfiguration的自动配置。

JpaRepositoriesAutoConfiguration

    @Configuration
    @ConditionalOnBean(DataSource.class)
    @ConditionalOnClass(JpaRepository.class)
    @ConditionalOnMissingBean({ JpaRepositoryFactoryBean.class,
    		JpaRepositoryConfigExtension.class })
    @ConditionalOnProperty(prefix = "spring.data.jpa.repositories", name = "enabled", havingValue = "true", matchIfMissing = true)
    @Import(JpaRepositoriesAutoConfigureRegistrar.class)
    @AutoConfigureAfter(HibernateJpaAutoConfiguration.class)
    public class JpaRepositoriesAutoConfiguration {}

spring-data-jpa底层使用的是Hibernate作为实现，所以jpa的自动配置操作在Hibernate的自动配置之后。



HibernateJpaAutoConfiguration

    @Configuration
    @ConditionalOnClass({ LocalContainerEntityManagerFactoryBean.class, EntityManager.class })
    @Conditional(HibernateEntityManagerCondition.class)
    @AutoConfigureAfter({ DataSourceAutoConfiguration.class })
    public class HibernateJpaAutoConfiguration extends JpaBaseConfiguration {
    
    	//other...
        
    	public HibernateJpaAutoConfiguration(DataSource dataSource,
    			JpaProperties jpaProperties,
    			ObjectProvider<JtaTransactionManager> jtaTransactionManager,
    			ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
            //调用父类的构造方法
    		super(dataSource, jpaProperties, jtaTransactionManager,
    				transactionManagerCustomizers);
    	}
    
    	@Override
    	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
    		return new HibernateJpaVendorAdapter();
    	}
    
    	@Override
    	protected Map<String, Object> getVendorProperties() {
    		Map<String, Object> vendorProperties = new LinkedHashMap<String, Object>();
    		vendorProperties.putAll(getProperties().getHibernateProperties(getDataSource()));
    		return vendorProperties;
    	}
        
        //other...
    }

在使用Spring和JPA集成的时候，会配置一个jpaVendorAdapter属性，一般使用HibernateJpaVendorAdapter作为JPA持久化实现厂商类。如下是spring和jpa集成时的部分配置：

    <property name="jpaVendorAdapter">
        <bean id="hibernateJpaVendorAdapter" class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
            <!-- 该bean的属性配置 -->
        </bean>
    </property>

	在JpaBaseConfiguration类中有个createJpaVendorAdapter()抽象方法，而在HibernateJpaAutoConfiguration类中进行了重载，创建使用HibernateJpaVendorAdapter作为JPA底层持久化实现厂商。



父类JpaBaseConfiguration

    @EnableConfigurationProperties(JpaProperties.class)
    @Import(DataSourceInitializedPublisher.Registrar.class)
    public abstract class JpaBaseConfiguration implements BeanFactoryAware {
    
    	private final DataSource dataSource;
    
    	private final JpaProperties properties;
    
    	private final JtaTransactionManager jtaTransactionManager;
    
    	private final TransactionManagerCustomizers transactionManagerCustomizers;
    
    	private ConfigurableListableBeanFactory beanFactory;
    
    	protected JpaBaseConfiguration(DataSource dataSource, JpaProperties properties,
    			ObjectProvider<JtaTransactionManager> jtaTransactionManager,
    			ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
    		this.dataSource = dataSource;
    		this.properties = properties;
    		this.jtaTransactionManager = jtaTransactionManager.getIfAvailable();
    		this.transactionManagerCustomizers = transactionManagerCustomizers
    				.getIfAvailable();
    	}
    
        //创建了TransactionManager的Bean
    	@Bean
    	@ConditionalOnMissingBean(PlatformTransactionManager.class)
    	public PlatformTransactionManager transactionManager() {
    		JpaTransactionManager transactionManager = new JpaTransactionManager();
    		if (this.transactionManagerCustomizers != null) {
    			this.transactionManagerCustomizers.customize(transactionManager);
    		}
    		return transactionManager;
    	}
    
        //创建了jpaVendorAdapter适配器，并填充相应属性，最后返回
    	@Bean
    	@ConditionalOnMissingBean
    	public JpaVendorAdapter jpaVendorAdapter() {
            //通过HibernateJpaAutoConfiguration子类创建HibernateJpaVendorAdapter适配器
    		AbstractJpaVendorAdapter adapter = createJpaVendorAdapter();
    		adapter.setShowSql(this.properties.isShowSql());
    		adapter.setDatabase(this.properties.determineDatabase(this.dataSource));
    		adapter.setDatabasePlatform(this.properties.getDatabasePlatform());
    		adapter.setGenerateDdl(this.properties.isGenerateDdl());
    		return adapter;
    	}
    
        //通过jpaVendorAdapter与其他配置信息创建Builder构建器
    	@Bean
    	@ConditionalOnMissingBean
    	public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
    			JpaVendorAdapter jpaVendorAdapter,
    			ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {
    		EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(
    				jpaVendorAdapter, this.properties.getProperties(),
    				persistenceUnitManager.getIfAvailable());
    		builder.setCallback(getVendorCallback());
    		return builder;
    	}
    
        //创建LocalContainerEntityManagerFactoryBean的Bean，用于JPA的容器管理EntityManagerFactory
    	@Bean
    	@Primary
    	@ConditionalOnMissingBean({ LocalContainerEntityManagerFactoryBean.class,
    			EntityManagerFactory.class })
    	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
    			EntityManagerFactoryBuilder factoryBuilder) {
    		Map<String, Object> vendorProperties = getVendorProperties();
    		customizeVendorProperties(vendorProperties);
    		return factoryBuilder.dataSource(this.dataSource).packages(getPackagesToScan())
    				.properties(vendorProperties).jta(isJta()).build();
    	}
    
        //other...
    }

以上配置信息类似一个xml配置：

    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driverClass}"></property>
        <property name="jdbcUrl" value="${jdbc.jdbcUrl}"></property>
        <property name="user" value="${jdbc.user}"></property>
        <property name="password" value="${jdbc.password}"></property>
        <property name="initialPoolSize" value="${jdbc.initialPoolSize}"></property>
        <property name="maxPoolSize" value="${jdbc.maxPoolSize}"></property>
    </bean>
    
    <!-- 配置jpa的EntityManagerFactory -->
    <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="dataSource" ref="dataSource"></property>
    
        <!-- 配置jpa生产商的适配器 -->
        <property name="jpaVendorAdapter">
            <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter"></bean>
        </property>
    
        <!-- 配置jpa的基本属性 -->
        <property name="jpaProperties">
            <props>
                <prop key="hibernate.show_sql">true</prop>
                <prop key="hibernate.format_sql">true</prop>
                <prop key="hibernate.hbm2ddl.auto">update</prop>
            </props>
        </property>
    </bean>
    
    <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
        <property name="entityManagerFactory" ref="entityManagerFactory"></property>
    </bean>
    
    <!-- 配置SpringData -->
    <jpa:repositories base-package="xxxxx包名" entity-manager-factory-ref="entityManagerFactory" transaction-manager-ref="transactionManager"></jpa:repositories>



五、JPA查询语法

5.1、JPA内置方法

Repository		Jpa基层接口

    public interface Repository<T, ID extends Serializable> {}



CrudRepository		专门用于crud的接口

    public interface CrudRepository<T, ID extends Serializable> extends Repository<T, ID> {
    	<S extends T> S save(S entity);
    
    	<S extends T> Iterable<S> save(Iterable<S> entities);
    
    	T findOne(ID id);
    
    	boolean exists(ID id);
    
    	Iterable<T> findAll();
    
    	Iterable<T> findAll(Iterable<ID> ids);
    
    	long count();
    
    	void delete(ID id);
    
    	void delete(T entity);
    
    	void delete(Iterable<? extends T> entities);
    
    	void deleteAll();
    }



PagingAndSortingRepository		带有简单的分页和排序的接口

    public interface PagingAndSortingRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
    
    	Iterable<T> findAll(Sort sort);
    
    	Page<T> findAll(Pageable pageable);
    }

    //排序类
    public class Sort implements Iterable<org.springframework.data.domain.Sort.Order>, Serializable {
    
        //默认以升序排序
    	public static final Direction DEFAULT_DIRECTION = Direction.ASC;
    
    	private final List<Order> orders;
    
    	public Sort(Order... orders) {
    		this(Arrays.asList(orders));
    	}
        
    	public Sort(List<Order> orders) {
    		if (null == orders || orders.isEmpty()) {
    			throw new IllegalArgumentException("You have to provide at least one sort property to sort by!");
    		}
    
    		this.orders = orders;
    	}
    
    	public Sort(String... properties) {
    		this(DEFAULT_DIRECTION, properties);
    	}
    
    	public Sort(Direction direction, String... properties) {
    		this(direction, properties == null ? new ArrayList<String>() : Arrays.asList(properties));
    	}
    
    	public Sort(Direction direction, List<String> properties) {
    		if (properties == null || properties.isEmpty()) {
    			throw new IllegalArgumentException("You have to provide at least one property to sort by!");
    		}
    
    		this.orders = new ArrayList<Order>(properties.size());
    
    		for (String property : properties) {
    			this.orders.add(new Order(direction, property));
    		}
    	}
    
    	public Sort and(Sort sort) {
    		if (sort == null) {
    			return this;
    		}
    
    		ArrayList<Order> these = new ArrayList<Order>(this.orders);
    
    		for (Order order : sort) {
    			these.add(order);
    		}
    
    		return new Sort(these);
    	}
    
    	public Order getOrderFor(String property) {
    		for (Order order : this) {
    			if (order.getProperty().equals(property)) {
    				return order;
    			}
    		}
    
    		return null;
    	}
    
        //other...
    
    	//内部定义了一个Direction的枚举类
    	public static enum Direction {
    
    		ASC, DESC;
            
            //other code...
    	}
    
    	//内部定义了Order类，用于字段排序
    	public static class Order implements Serializable {
    
    		private static final boolean DEFAULT_IGNORE_CASE = false;
    
    		private final Direction direction;
    		private final String property;
    		private final boolean ignoreCase;
    		private final NullHandling nullHandling;
    
    		public Order(Direction direction, String property) {
    			this(direction, property, DEFAULT_IGNORE_CASE, null);
    		}
    
    		public Order(Direction direction, String property, NullHandling nullHandlingHint) {
    			this(direction, property, DEFAULT_IGNORE_CASE, nullHandlingHint);
    		}
    
    		public Order(String property) {
    			this(DEFAULT_DIRECTION, property);
    		}
    
    		private Order(Direction direction, String property, boolean ignoreCase, NullHandling nullHandling) {
    
    			if (!StringUtils.hasText(property)) {
    				throw new IllegalArgumentException("Property must not null or empty!");
    			}
    
    			this.direction = direction == null ? DEFAULT_DIRECTION : direction;
    			this.property = property;
    			this.ignoreCase = ignoreCase;
    			this.nullHandling = nullHandling == null ? NullHandling.NATIVE : nullHandling;
    		}
    
            //other...
    	}
    }

    //分页接口
    public interface Pageable {
    
    	int getPageNumber();
    
    	int getPageSize();
    
    	int getOffset();
    
    	Sort getSort();
    
    	Pageable next();
    
    	Pageable previousOrFirst();
    
    	Pageable first();
    
    	boolean hasPrevious();
    }

    //分页抽象类
    public abstract class AbstractPageRequest implements Pageable, Serializable {
    
    	private final int page;
    	private final int size;
    
    	public AbstractPageRequest(int page, int size) {
    
            //other code...
    		
    		this.page = page;
    		this.size = size;
    	}
    
    	public int getPageSize() {
    		return size;
    	}
    
    	public int getPageNumber() {
    		return page;
    	}
    
    	public int getOffset() {
    		return page * size;
    	}
    
    	public boolean hasPrevious() {
    		return page > 0;
    	}
    
    	public Pageable previousOrFirst() {
    		return hasPrevious() ? previous() : first();
    	}
    
    	public abstract Pageable next();
    
    	public abstract Pageable previous();
    
    	public abstract Pageable first();
    
    	//other code...
    }

    //分页实现类
    public class PageRequest extends AbstractPageRequest {
    
    	private final Sort sort;
    
    	public PageRequest(int page, int size) {
    		this(page, size, null);
    	}
    
    	public PageRequest(int page, int size, Direction direction, String... properties) {
    		this(page, size, new Sort(direction, properties));
    	}
    
    	public PageRequest(int page, int size, Sort sort) {
    		super(page, size);
    		this.sort = sort;
    	}
    
    	public Sort getSort() {
    		return sort;
    	}
    
    	public Pageable next() {
    		return new PageRequest(getPageNumber() + 1, getPageSize(), getSort());
    	}
    
    	public PageRequest previous() {
    		return getPageNumber() == 0 ? this : new PageRequest(getPageNumber() - 1, getPageSize(), getSort());
    	}
    
    	public Pageable first() {
    		return new PageRequest(0, getPageSize(), getSort());
    	}
    
    	//other code...
    }



JpaRepository		带有crud接口和分页、排序的接口

    public interface JpaRepository<T, ID extends Serializable>
    		extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
    
    	List<T> findAll();
    
    	List<T> findAll(Sort sort);
    
    	List<T> findAll(Iterable<ID> ids);
    
    	<S extends T> List<S> save(Iterable<S> entities);
    
    	void flush();
    
    	<S extends T> S saveAndFlush(S entity);
    
    	void deleteInBatch(Iterable<T> entities);
    
    	void deleteAllInBatch();
    
    	T getOne(ID id);
    
    	@Override
    	<S extends T> List<S> findAll(Example<S> example);
    
    	@Override
    	<S extends T> List<S> findAll(Example<S> example, Sort sort);
    
    }



JpaSpecificationExecutor：用于复杂的条件查询并分页、排序功能

    public interface JpaSpecificationExecutor<T> {
    
    	T findOne(Specification<T> spec);
    
    	List<T> findAll(Specification<T> spec);
    
    	Page<T> findAll(Specification<T> spec, Pageable pageable);
    
    	List<T> findAll(Specification<T> spec, Sort sort);
    
    	long count(Specification<T> spec);
    }

    public interface Specification<T> {
    
    	Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);
    }



    public class Specifications<T> implements Specification<T>, Serializable {
    
    	private final Specification<T> spec;
    
    	private Specifications(Specification<T> spec) {
    		this.spec = spec;
    	}
    
    	public static <T> Specifications<T> where(Specification<T> spec) {
    		return new Specifications<T>(spec);
    	}
    
    	public Specifications<T> and(Specification<T> other) {
    		return new Specifications<T>(new ComposedSpecification<T>(spec, other, AND));
    	}
    
    	public Specifications<T> or(Specification<T> other) {
    		return new Specifications<T>(new ComposedSpecification<T>(spec, other, OR));
    	}
    
    	public static <T> Specifications<T> not(Specification<T> spec) {
    		return new Specifications<T>(new NegatedSpecification<T>(spec));
    	}
    
    	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    		return spec == null ? null : spec.toPredicate(root, query, builder);
    	}
    
        //other code...
    }

以上一大堆JPA内置方法，可以直接使用，而无需再声明定义了。当然如果想重载或者自定义的话，可以重新声明定义下。



5.2、通过解析方法名创建查询

  Keyword          	Sample                                  	JPQL snippet                            
  And              	findByLastnameAndFirstname              	… where x.lastname = ?1 and x.firstname = ?2
  Or               	findByLastnameOrFirstname               	… where x.lastname = ?1 or x.firstname = ?2
  Is,Equals        	findByFirstname,findByFirstnameIs,findByFirstnameEquals	… where x.firstname = ?1                
  Between          	findByStartDateBetween                  	… where x.startDate between ?1 and ?2   
  LessThan         	findByAgeLessThan                       	… where x.age < ?1                      
  LessThanEqual    	findByAgeLessThanEqual                  	… where x.age ⇐ ?1                      
  GreaterThan      	findByAgeGreaterThan                    	… where x.age > ?1                      
  GreaterThanEqual 	findByAgeGreaterThanEqual               	… where x.age >= ?1                     
  After            	findByStartDateAfter                    	… where x.startDate > ?1                
  Before           	findByStartDateBefore                   	… where x.startDate < ?1                
  IsNull           	findByAgeIsNull                         	… where x.age is null                   
  IsNotNull,NotNull	findByAge(Is)NotNull                    	… where x.age not null                  
  Like             	findByFirstnameLike                     	… where x.firstname like ?1             
  NotLike          	findByFirstnameNotLike                  	… where x.firstname not like ?1         
  StartingWith     	findByFirstnameStartingWith             	… where x.firstname like ?1(parameter bound with appended %)
  EndingWith       	findByFirstnameEndingWith               	… where x.firstname like ?1(parameter bound with prepended %)
  Containing       	findByFirstnameContaining               	… where x.firstname like ?1(parameter bound wrapped in%)
  OrderBy          	findByAgeOrderByLastnameDesc            	… where x.age = ?1 order by x.lastname desc
  Not              	findByLastnameNot                       	… where x.lastname <> ?1                
  In               	findByAgeIn(Collection<Age> ages)       	… where x.age in ?1                     
  NotIn            	findByAgeNotIn(Collection<Age> age)     	… where x.age not in ?1                 
  True             	findByActiveTrue()                      	… where x.active = true                 
  False            	findByActiveFalse()                     	… where x.active = false                
  IgnoreCase       	findByFirstnameIgnoreCase               	… where UPPER(x.firstame) = UPPER(?1)   

例如：

- findByLastName(String lastName)：表示根据lastName精确查询
- findByLastNameLike(String lastName)：表示根据lastName模糊查询
- findByAgeBetween(int min, int max)：表示查询年龄在[min,max]之间的数据
- findByAgeEquals(int age)：表示查询年龄等于age的数据
- findByAgeAndGender(int age, String gender)：表示根据年龄等于age且性别为gender的数据
- 等等...



5.3、使用自定义的@Query注解进行查询

    //使用自定义的@Query注解，实现sql语句
    @Query("select count(*) from Person")
    Long findAllCount();
    
    @Query("from Person where id = ?1")
    List<Person> selectWhereById(Integer id);
    
    @Query("delete from Person where id = ?1")
    @Modifying
    void deleteById(Integer id);
    
    @Query(value="select distinct id from person", nativeQuery = true)
    List<Integer> getIds();

注意：

- 使用@Modifying注解表示该Query是个增删改的操作，需要事务管理。
- 指定nativeQuery=true，则说明该Query的sql语句为原生SQL，而不是hql，默认为false，即使用hql查询。



六、JPA常用注解

- @Entity：设置Pojo为实体
- @Table：设置表名
- @Id：设置主键
- @Column：设置普通列名
  - name：字段名，默认为空串
  - unique：是否唯一，默认为false
  - nullable：是否可以为空，默认为true
  - insertable：是否可插入，默认为true
  - updatable：是否可更新，默认为true
  - length：列值最大长度，默认为255
  - precision：精度，即数字长度，默认为0
  - scale：小数点后位数，默认为0
- @GeneratedValue：主键生成策略，有AUTO, TABLE, SEQUENCE, IDENTITY四种属性值
- @SequenceGenerator：按照序列生成主键的序列名
- @Transient：临时字段，不需要跟数据库映射的字段
- @Temporal：时间格式，有DATE, TIME, TIMESTAMP三种属性值
- @Embedded：注解属性，用来表示该属性的类是个嵌入类，同时嵌入类必须使用@Embeddable注解
- @Embeddable：注解类，表示该类是个嵌入类，会被嵌入到其他类中
- @JoinColumn：定义外键列的属性
  - name：外键列的名称
  - referencedColumnName：参考列，默认值为关联表的主键。
- @OneToOne：定义1-1的关联关系
- @OneToMany：定义1-n的关联关系
- @ManyToOne：定义n-1的关联关系
- @ManyToMany：定义n-n的关联关系
  - cascade：关联属性，这个属性定义了当前类对象操作了之后，级联对象的操作。有ALL，PERSIST，MERGE，REMOVE，REFRESH，DETACH六种属性值。
  - fetch：级联数据加载策略
    - LAZY：表示关系类在被访问时才被加载（默认）
    - EAGER：表示关系类在主类加载的时候同时加载
  - mappedBy：拥有关联关系的域，如果关系是单向的就不需要。双向关系表，那么拥有关系的这一方有建立、解除和更新与另一方关系的能力，而另一方没有，只能被动管理，这个属性被定义在关系的被拥有方。双向@OneToOne，双向@OneToMany，双向@ManyToMany中均需要有此属性。注意点：该属性与@JoinColumn互斥，不能同时出现在类的属性上。



具体注解使用请参看我的GitHub-java-jpa或者码云-java-jpa。



七、使用JPA操作数据库

附上代码：

实体类Person

    @Entity
    @Table
    public class Person implements Serializable{
    
    	@Id
    	@GeneratedValue
    	private Integer id;
    
    	private String name;
    
    	private int age;
    	
        //getter和setter
    }



控制器类PersonController

    @RestController
    @RequestMapping("/person")
    public class PersonController {
    
    	@Autowired
    	private IPersonService personService;
    
    	@RequestMapping("/find")
    	public Person findPersonByName(String name){
    		return personService.findPersonByName(name);
    	}
    
    	@PostMapping("/")
    	public String insertIntoPerson(Person person){
    		personService.insertIntoPerson(person);
    		return "success";
    	}
    
    	@GetMapping("/age")
    	public List<Person> getPersonAgeBetween(int min, int max){
    		return personService.getPersonAgeBetween(min, max);
    	}
    
    	@GetMapping("/page")
    	public Page<Person> getPersonsByPage(int pageNum, int pageSize){
    		return personService.getPersonsByPage(pageNum, pageSize);
    	}
    
    	@GetMapping("/sort")
    	public Page<Person> getPersonsBySortAge(){
    		return personService.getPersonsBySortAge();
    	}
    
    	@GetMapping("/count")
    	public Long getAllCount(){
    		return personService.findAllCount();
    	}
    
    	@PostMapping("/update")
    	public void updateOnePerson(Person person){
    		personService.updateOnePerson(person.getAge(), person.getName(), person.getId());
    	}
    
    	@GetMapping("/ids")
    	public List<Integer> getIds(){
    		return personService.getIds();
    	}
    }



业务层接口IPersonService

    public interface IPersonService {
    
    	Person findPersonByName(String name);
    
    	void insertIntoPerson(Person person);
    
    	List<Person> getPersonAgeBetween(int min, int max);
    
    	Page<Person> getPersonsByPage(int pageNum, int pageSize);
    
    	Page<Person> getPersonsBySortAge();
    
    	Long findAllCount();
    
    	void updateOnePerson(int age, String name, int id);
    
    	List<Integer> getIds();
    }



业务层接口实现类PersonServiceImpl

    @Service
    public class PersonServiceImpl implements IPersonService {
    
    	@Autowired
    	private IPersonRepository personRepository;
    
    	@Override
    	public Person findPersonByName(String name) {
    		return personRepository.findPersonByName(name);
    	}
    
    	@Override
    	public void insertIntoPerson(Person person) {
    		personRepository.save(person);
    	}
    
    	@Override
    	public List<Person> getPersonAgeBetween(int min, int max) {
    		return personRepository.findPersonByAgeBetween(min, max);
    	}
    
    	@Override
    	public Page<Person> getPersonsByPage(int pageNum, int pageSize) {
    		Pageable pageable = new PageRequest(pageNum, pageSize);
    		return personRepository.findAll(pageable);
    	}
    
    	@Override
    	public Page<Person> getPersonsBySortAge() {
    		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "age"));
    		Pageable pageable = new PageRequest(0,5, sort);
    		return personRepository.findAll(pageable);
    	}
    
    	@Override
    	public Long findAllCount() {
    		return personRepository.findAllCount();
    	}
    
    	@Transactional
    	@Override
    	public void updateOnePerson(int age, String name, int id){
    		personRepository.updateOnePerson(age, name, id);
    	}
    
    	@Override
    	public List<Integer> getIds() {
    		return personRepository.getIds();
    	}
    }



持久化接口IPersonRepository

    public interface IPersonRepository extends JpaRepository<Person, Integer>{
    
    	Person findPersonByName(String name);
    
    	List<Person> findPersonByAgeBetween(int min, int max);
    
    	//使用自定义的@Query注解，实现sql语句
    	@Query("select count(*) from Person")
    	Long findAllCount();
    
    	//使用@Modifying注解表示该hql是个增删改的操作，需要事务管理
    	@Query("update Person set age = ?1, name = ?2 where id = ?3")
    	@Modifying
    	void updateOnePerson(int age, String name, int id);
    
    	//原生SQL
    	@Query(value="select distinct id from person", nativeQuery = true)
    	List<Integer> getIds();
    }

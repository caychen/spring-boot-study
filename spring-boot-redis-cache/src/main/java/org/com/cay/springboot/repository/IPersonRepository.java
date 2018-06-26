package org.com.cay.springboot.repository;

import org.com.cay.springboot.entity.Person;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Cay on 2017/9/7.
 */
@CacheConfig(cacheNames = "person")
// 如果在@Cacheable中添加cacheNames或者value属性，则可以省略该注解；
// 如果在@Cacheable中未添加cacheNames或者value属性，则必须在@CacheConfig中指定cacheNames或者value属性
public interface IPersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {

	@Cacheable(key = "#p0")//#p0表示函数的第一个参数
	Person findPersonByName(String name);

	List<Person> findPersonByAgeBetween(int min, int max);


	//使用自定义的@Query注解，实现sql语句
	@Query("select count(*) from Person")
	Long findAllCount();

	@Query("update Person set age = ?1 where id = ?2")
	@Modifying
	void updateOnePerson(int age, int id);

	@Override
	@CachePut(key = "#p0.name")
	Person save(Person person);
}

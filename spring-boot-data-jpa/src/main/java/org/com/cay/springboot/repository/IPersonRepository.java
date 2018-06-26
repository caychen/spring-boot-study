package org.com.cay.springboot.repository;

import org.com.cay.springboot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Cay on 2017/9/7.
 */
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

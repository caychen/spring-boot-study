package org.com.cay.springboot.mapper;

import org.apache.ibatis.annotations.*;
import org.com.cay.springboot.entity.Person;

import java.util.List;

/**
 * Created by Cay on 2018/4/4.
 */
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

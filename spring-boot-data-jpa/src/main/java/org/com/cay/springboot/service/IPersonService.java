package org.com.cay.springboot.service;

import org.com.cay.springboot.entity.Person;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Cay on 2017/9/7.
 */
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

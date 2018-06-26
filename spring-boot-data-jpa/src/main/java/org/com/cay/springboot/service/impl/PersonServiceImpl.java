package org.com.cay.springboot.service.impl;

import org.com.cay.springboot.entity.Person;
import org.com.cay.springboot.repository.IPersonRepository;
import org.com.cay.springboot.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Cay on 2017/9/7.
 */
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

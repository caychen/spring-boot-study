package org.com.cay.springboot.controller;

import org.com.cay.springboot.entity.Person;
import org.com.cay.springboot.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Cay on 2017/9/7.
 */
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
		personService.updateOnePerson(person.getAge(), person.getId());
	}
}

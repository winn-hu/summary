package com.blueStarWei.aop;

import org.springframework.stereotype.Service;

@Service("personService")
public class PersonServiceImpl implements PersonService{

	public void printPerson(){
		System.out.println("Person");
	}
}

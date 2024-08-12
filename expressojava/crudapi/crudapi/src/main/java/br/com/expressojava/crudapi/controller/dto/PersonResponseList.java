package br.com.expressojava.crudapi.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.expressojava.crudapi.domain.Person;

public class PersonResponseList {
	@JsonProperty("pessoas")
	private List<PersonResponse> personList;
	
	

	public PersonResponseList(List<PersonResponse> personList) {
		this.personList = personList;
	}



	public static PersonResponseList fromDomain(List<Person> personList) {
		List<PersonResponse> people = personList.stream()
				.map(PersonResponse::fromDomain)
				.toList();
				
		
		return new PersonResponseList(people);
	}
	
}

package br.com.expressojava.crudapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.expressojava.crudapi.controller.dto.PersonRequest;
import br.com.expressojava.crudapi.controller.dto.PersonResponse;
import br.com.expressojava.crudapi.controller.dto.PersonResponseList;
import br.com.expressojava.crudapi.domain.Person;
import br.com.expressojava.crudapi.persistance.PersonPersistance;

@RestController
@RequestMapping("/v1/person")
public class PersonController {
	private final PersonPersistance personPersistance;
	
	
	public PersonController(PersonPersistance personPersistance) {
		this.personPersistance = personPersistance;
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonResponse> getPessoa(@PathVariable("id") long id) {
		Optional<Person> foundPerson = personPersistance.getById(id);
		return ResponseEntity.of(PersonResponse.fromDomain(foundPerson));
		
	}
	
	@GetMapping
	public ResponseEntity<PersonResponseList> getAll(){
		List<Person> personList = personPersistance.getAll();
		
		return ResponseEntity.ok(PersonResponseList.fromDomain(personList));
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody PersonRequest personRequest) {
		personPersistance.create(personRequest.toModel());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeById(@PathVariable("id") long id){
		personPersistance.deleteById(id);
	}
}

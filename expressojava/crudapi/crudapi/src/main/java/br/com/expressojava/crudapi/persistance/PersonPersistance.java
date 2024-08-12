package br.com.expressojava.crudapi.persistance;

import java.util.List;
import java.util.Optional;

import br.com.expressojava.crudapi.domain.Person;

public interface PersonPersistance {

	Optional<Person> getById(long id);
	List<Person> getAll();
	void create(Person person);
	void update(Person person);
	void deleteById(long id);
}
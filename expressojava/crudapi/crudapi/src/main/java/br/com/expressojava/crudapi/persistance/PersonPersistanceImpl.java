package br.com.expressojava.crudapi.persistance;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.expressojava.crudapi.domain.Person;

@Service
public class PersonPersistanceImpl implements PersonPersistance{
	List<Person> database = new LinkedList<>();

	@Override
	public Optional<Person> getById(long id) {
		return database.stream()
				.filter(person -> person.getId() == id)
				.findFirst();
	}

	@Override
	public List<Person> getAll() {
		return database;
	}

	@Override
	public void create(Person person) {
		database.add(person);
	}

	@Override
	public void update(Person person) {
		Optional<Person> optFoundPerson = getById(person.getId());
		if(optFoundPerson.isPresent()) {
			var foundPerson = optFoundPerson.get();
			foundPerson.setAge(person.getAge());
			foundPerson.setName(person.getName());
			foundPerson.setPhone(person.getPhone());
		}
	}

	@Override
	public void deleteById(long id) {
		database.removeIf(person -> person.getId() == id);
	}
	
}

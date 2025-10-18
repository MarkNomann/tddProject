package es.nomann.tddproject.service;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.dto.Person;
import es.nomann.tddproject.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person createPerson(Long id, String username, String email) {
        Person person = new Person();
        person.setId(id);
        person.setUsername(username);
        person.setEmail(email);
        repository.save(person);
        return person;
    }

    public Person findPerson(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Person deletePerson(Long id) {
        Person person = repository.findById(id).orElse(null);
        repository.delete(person);
        return person;
    }





}

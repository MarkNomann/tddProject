package es.nomann.tddproject;

import es.nomann.tddproject.dto.Person;
import es.nomann.tddproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person createPerson() {
        Person p = new Person();
        p.setId(11L);
        p.setUsername("test");
        p.setEmail("test@test");
        repository.save(p);
        return p;
    }
}

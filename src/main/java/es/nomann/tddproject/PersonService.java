package es.nomann.tddproject;

import es.nomann.tddproject.dto.City;
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

    public Person createPerson(Long id, String username, String email, City city) {
        Person p = new Person();
        p.setId(id);
        p.setUsername(username);
        p.setEmail(email);
        p.setCity(city);
        repository.save(p);
        return p;
    }
}

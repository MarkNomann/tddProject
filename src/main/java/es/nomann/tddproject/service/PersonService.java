package es.nomann.tddproject.service;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.dto.Person;
import es.nomann.tddproject.repository.CityRepository;
import es.nomann.tddproject.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final CityRepository cityRepository;

    public PersonService(PersonRepository repository, CityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
    }

    public Person createPerson(String username, String email) {
        Person person = new Person();
        person.setUsername(username);
        person.setEmail(email);
        repository.save(person);
        return person;
    }

    public Person findPerson(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Person findPersonByUsername(String username) {
        return repository.findByName(username);
    }

    public Person deletePerson(Long id) {
        Person person = repository.findById(id).orElse(null);
        if (person != null) {
            repository.delete(person);
        }
        return person;
    }

    @Transactional
    public Person assignCityToPerson(String username, String cityName) {
        Person person = findPersonByUsername(username);
        var city = cityRepository.findByName(cityName);
        if ( city != null) {
            person.setCity(city);
            repository.save(person);
        }
        return person;
    }

}

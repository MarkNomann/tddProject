package es.nomann.tddproject.service;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.dto.Person;
import es.nomann.tddproject.repository.CityRepository;
import es.nomann.tddproject.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final CityRepository cityRepository;

    public PersonService(PersonRepository repository, CityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
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

    @Transactional
    public Person assignCityToPerson(Long idPerson, Long idCity) {
        Person person = findPerson(idPerson);
        person.setCity(cityRepository.findById(idCity).orElse(null));
        repository.save(person);
        System.out.println(person.getCity().getId());
        return person;
    }





}

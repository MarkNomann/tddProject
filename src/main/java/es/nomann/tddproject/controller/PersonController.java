package es.nomann.tddproject.controller;

import es.nomann.tddproject.dto.AssignPersonReq;
import es.nomann.tddproject.dto.Person;
import es.nomann.tddproject.service.CityService;
import es.nomann.tddproject.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonService personService;
    private final CityService cityService;

    public PersonController(final PersonService personService, final CityService cityService) {
        this.personService = personService;
        this.cityService = cityService;
    }

    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(Person person) {
       return ResponseEntity.ok(personService.createPerson(person.getUsername(),person.getEmail()));
    }

    @PostMapping("/assign")
    public ResponseEntity<Person> assignPersonToCity(@RequestBody AssignPersonReq assignPersonReq) {
        var city = cityService.findByName(assignPersonReq.cityName());
        var person = personService.findPersonByUsername(assignPersonReq.username());
        if (person == null || city == null) {
            return ResponseEntity.notFound().build();
        }
        personService.assignCityToPerson(person.getUsername(),city.getName());
        return ResponseEntity.ok(person);
    }
}

package es.nomann.tddproject;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.repository.CityRepository;
import es.nomann.tddproject.repository.PersonRepository;
import es.nomann.tddproject.service.CityService;
import es.nomann.tddproject.service.PersonService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({PersonService.class,CityService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IntegrationPersonTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private CityService cityService;

    @ParameterizedTest
    @CsvSource({
            "ullf, soko@gmail.com",
            "anna, anna@example.com",
            "john, john@doe.com",
            "Mark, mark@doe.com"
    })
    public void testCreatePerson(String name, String email) {
        var person = personService.createPerson(name,email);

        assertNotNull(person);
        assertEquals(name,person.getUsername());
    }

    @ParameterizedTest
    @CsvSource({
            "ullf, soko@gmail.com",
            "anna, anna@example.com",
            "john, john@doe.com",
            "Mark, mark@doe.com"
    })
    public void deletePerson(String name, String email) {
        personService.createPerson(name,email);
        personService.deletePerson(personService.findPersonByUsername(name).getId());

        assertNull(personService.findPersonByUsername(name));
    }

    @ParameterizedTest
    @CsvSource({
            "ullf, soko@gmail.com",
            "anna, anna@example.com",
            "john, john@doe.com",
            "Mark, mark@doe.com"
    })
    public void testAssignCity(String name, String email) {
        personService.createPerson(name,email);
        City city = new City("Paris");
        cityService.addCity(city);
        var ret = personService.assignCityToPerson(personService.findPersonByUsername(name).getUsername(),city.getName());

        assertNotNull(ret.getCity());
        assertEquals("Paris",personService.findPersonByUsername(name).getCity().getName());
    }

    @Test
    public void testDeletePersons() {
        HashMap<String,String> persons = new HashMap<>();
        persons.put("Ullf","soko@gmail.com");
        persons.put("Anna","anna@example.com");
        persons.put("John","john@doe.com");
        persons.put("Mark","mark@doe.com");

        persons.forEach((name,email)->{
            personService.createPerson(name,email);
        });

        persons.forEach((name,email)->{
            personService.deletePerson(personService.findPersonByUsername(name).getId());
        });

        assertNull(personService.findPersonByUsername("Ullf"));
        assertNull(personService.findPersonByUsername("John"));
        assertNull(personService.findPersonByUsername("Mark"));
        assertNull(personService.findPersonByUsername("Anna"));
    }
}

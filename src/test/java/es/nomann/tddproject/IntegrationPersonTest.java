package es.nomann.tddproject;

import es.nomann.tddproject.repository.CityRepository;
import es.nomann.tddproject.repository.PersonRepository;
import es.nomann.tddproject.service.PersonService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(PersonService.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IntegrationPersonTest {

    @Autowired
    private PersonService personService;


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
            "ullf","john"
    })
    public void deletePerson(String name) {
        personService.deletePerson(personService.findPersonByUsername(name).getId());
        assertNull(personService.findPersonByUsername(name));
    }

    @Test
    public void testAssignCity() {
        var ret = personService.assignCityToPerson(personService.findPersonByUsername("anna").getId(),1L);
        assertNotNull(ret.getCity());
    }

  /*  @Test
    public void testToTest() {
        assertEquals(2,2);
    }*/
}

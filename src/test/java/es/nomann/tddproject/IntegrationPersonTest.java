package es.nomann.tddproject;

import es.nomann.tddproject.repository.CityRepository;
import es.nomann.tddproject.repository.PersonRepository;
import es.nomann.tddproject.service.PersonService;
import org.aspectj.lang.annotation.Before;
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
    private PersonService service;

    @ParameterizedTest
    @CsvSource({
            "23, ullf, soko@gmail.com",
            "45, anna, anna@example.com",
            "99, john, john@doe.com",
            "150, Mark, mark@doe.com"
    })
    public void testCreatePerson(Long id, String name, String email) {
        var person = service.createPerson(id,name,email);
        assertNotNull(person);
        assertEquals(name,person.getUsername());
    }

    @ParameterizedTest
    @ValueSource(longs = {45,23,99})
    public void deletePerson(Long id) {
        service.deletePerson(id);
        assertNull(service.findPerson(id));
    }

    @Test
    public void testAssignCity() {
        var ret = service.assignCityToPerson(150L,1L);
        assertNotNull(ret.getCity());
    }

  /*  @Test
    public void testToTest() {
        assertEquals(2,2);
    }*/
}

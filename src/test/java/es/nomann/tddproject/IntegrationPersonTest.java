package es.nomann.tddproject;

import es.nomann.tddproject.dto.City;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
            "99, john, john@doe.com"
    })
    public void testCreatePerson(Long id, String name, String email) {
        City city = new City();
       var person = service.createPerson(id,name,email,city);
       assertNotNull(person);
       assertEquals(name,person.getUsername());
    }
}

package es.nomann.tddproject;

import es.nomann.tddproject.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import(PersonService.class)
public class IntegrationPersonTest {

    @Autowired
    private PersonService service;

    @Test
    public void testCreatePerson() {
       var person = service.createPerson();
       assertNotNull(person);
       assertEquals( "test@test",person.getEmail());
    }
}

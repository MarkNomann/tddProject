package es.nomann.tddproject;


import es.nomann.tddproject.dto.Person;
import es.nomann.tddproject.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class IntegrationRepositoryTest {

    private Person testPerson;

    @Autowired
    private PersonRepository repository;

    @BeforeEach
    public void setup() {
        testPerson = new Person("mark", "mark@gmail.com",null);
    }

    @Test
    public void testCreatePerson() {
        repository.save(testPerson);
        assertEquals("mark",testPerson.getUsername());
        assertEquals("mark@gmail.com",testPerson.getEmail());
        assertNotNull(testPerson);
    }

    @Test
    public void testFindPersonById() {
        Person person = new Person("person1","person1gmail.com",null);
        repository.save(person);
        var found = repository.findById(person.getId());
        assertNotNull(found);
        assertEquals("person1",person.getUsername());
    }

}

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
        testPerson = new Person(1L,"mark", "mark@gmail.com");
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
        Person person = new Person(2L,"person1","person1gmail.com");
        repository.save(person);
        var found = repository.findById(person.getId());
        assertNotNull(found);
        assertEquals("person1",person.getUsername());
    }

    @Test
    public void testDeletePerson() {
        repository.delete(testPerson);
        assertThrows(NoSuchElementException.class,() -> repository.findById(testPerson.getId()).get());
    }

}

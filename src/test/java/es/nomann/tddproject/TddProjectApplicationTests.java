package es.nomann.tddproject;

import es.nomann.tddproject.dto.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TddProjectApplicationTests {


    @Test
    public void createPerson() {
        Person p = new Person("mark","mark@gmail.com",null);
        assertNotNull(p);
    }

}

package es.nomann.tddproject;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.dto.Person;
import es.nomann.tddproject.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IntegrationCityTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testAddCity() {
        City city = new City(1L,"Moscow");
        cityRepository.save(city);
        Person person = new Person(1L,"1","Moscow");
        Person person1 = new Person(2L,"2","London");
        city.setPeople(person);
        person.setCity(city);
        city.setPeople(person1);
        person1.setCity(city);
        assertThat(city.getPeople().size()).isEqualTo(2);
        assertEquals("2", city.getPeople().get(1).getUsername());
    }
}

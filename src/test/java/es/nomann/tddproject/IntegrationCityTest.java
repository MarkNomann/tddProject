package es.nomann.tddproject;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.dto.Person;
import es.nomann.tddproject.repository.CityRepository;
import es.nomann.tddproject.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IntegrationCityTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    @Commit
    public void testAddCity() {
        City city = new City(1L,"Moscow");
        cityRepository.save(city);
        assertThat(city.getPeople().size()).isEqualTo(0);
    }
}

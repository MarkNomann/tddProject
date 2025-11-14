package es.nomann.tddproject;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.dto.Street;
import es.nomann.tddproject.repository.CityRepository;
import es.nomann.tddproject.service.CityService;
import es.nomann.tddproject.service.StreetService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import({StreetService.class, CityService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IntegrationStreetTest {

    @Autowired
   private StreetService streetService;
    @Autowired
   private CityService cityService;

    @BeforeEach
    public void setup() {
        City city = new City();
        city.setName("Riga");
        cityService.addCity(city);
    }

    @ParameterizedTest
    @ValueSource(strings = {"main","first","second","third","this","tha"})
    public void testAddStreetToCity(String name) {
        Street chai = new Street();
        chai.setName(name);

        streetService.setToCity(chai,cityService.findByName("Riga"));
        streetService.saveStreet(chai);
        var found = streetService.findStreetByName(name);
        assertNotNull(found);
        assertEquals(name,found.getName());
    }



    @AfterEach
    public void cleanup() {
      // cityService.deleteCity(cityService.findById(1L));

    }
}

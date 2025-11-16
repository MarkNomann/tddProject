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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private final String cityToTest = "Riga";

    @BeforeEach
    public void setup() {
        City city = new City();
        city.setName(cityToTest);
        cityService.addCity(city);
    }

    @ParameterizedTest
    @ValueSource(strings = {"main","first","second","third","this","tha"})
    public void testAddStreetToCity(String name) {
        Street newStreet = new Street();
        newStreet.setName(name);
        streetService.setToCity(newStreet,cityService.findByName(cityToTest));
        streetService.saveStreet(newStreet);
        var found = streetService.findStreetByName(name);
        assertNotNull(found);
        assertEquals(name,found.getName());
    }

    @Test
    public void testSetNeighbours() {
        var city = cityService.findByName(cityToTest);
        var newStreetsNeighbors = List.of(new Street("main",city,null),new Street("first",city,null),new Street("second",city,null),new Street("third",city,null));
        city.setStreets(newStreetsNeighbors);
        var neigbors = city.getStreets().stream()
                .filter(street -> street.getName().equals("main") || street.getName().equals("first")).collect(Collectors.toSet());
        var out = streetService.findStreetByName("second");
        streetService.setNeighbors(out,neigbors);
        assertThat(!city.getStreets().isEmpty());
        assertEquals(newStreetsNeighbors.size(),city.getStreets().size());
        assertEquals(2,streetService.findStreetByName("second").getNeighbors().size());
    }



    @AfterEach
    public void cleanup() {
      // cityService.deleteCity(cityService.findById(1L));

    }
}

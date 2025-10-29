package es.nomann.tddproject;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.dto.Street;
import es.nomann.tddproject.repository.CityRepository;
import es.nomann.tddproject.service.CityService;
import es.nomann.tddproject.service.StreetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import({StreetService.class, CityService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IntegrationStreetTest {

    @Autowired
   private StreetService streetService;
    @Autowired
   private CityService cityService;

    @BeforeEach
    @Commit
    public void setup() {
        City city = new City();
        city.setId(1L);
        city.setName("Mpscow");
        cityService.addCity(city);
    }

    @Test
    @Commit
    public void testAddCity() {
       // var streets = cityService.findById(1L).getStreets();
        Street chai = new Street();
        chai.setName("Chai");

       // streetService.saveStreet(chai);
       //streetService.saveStreet(chai2);

        streetService.setToCity(chai,cityService.findById(1L));
        streetService.saveStreet(chai);


        Street chai2 = new Street();
        chai2.setName("Chai2");
        streetService.setToCity(chai2,cityService.findById(1L));
        streetService.saveStreet(chai2);
        /* streetService.setToCity(chai2,cityService.findById(1L));

        streetService.saveStreet(chai2);*/

    }

    @AfterEach
    @Commit
    public void cleanup() {
      // cityService.deleteCity(cityService.findById(1L));

    }
}

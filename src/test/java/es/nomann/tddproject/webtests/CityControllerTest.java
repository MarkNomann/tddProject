package es.nomann.tddproject.webtests;

import es.nomann.tddproject.controller.CityController;
import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.assertj.core.api.AssertionsForClassTypes.*;
import static org.assertj.core.api.Assertions.assertThat;


@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    MockMvcTester client;

    @MockitoBean
    private CityService cityService;


    @Test
    void basicTest() {
        assertThat(client.get().uri("/cities")).hasStatusOk();

    }

    @Test
    void newCityTest() {
        assertThat(client.post().uri("/city")
               .content("{\"name\":\"New City\"}")
               .contentType(MediaType.APPLICATION_JSON)
               .exchange().getResponse().getStatus())
               .isEqualTo(HttpStatus.CREATED.value());
    }

}

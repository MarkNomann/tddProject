package es.nomann.tddproject.controller;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    final private CityService cityService;

    public CityController(final CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> cities() {
        return new ResponseEntity<>(cityService.cities(), HttpStatus.OK);
    }

    @PostMapping("/city")
    public ResponseEntity<City> newCity(@RequestBody String name) {
        City city = new City();
        city.setName(name);
        cityService.addCity(city);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping("/city/{cityname}")
    public ResponseEntity<City> getCity(@PathVariable("cityname") String cityname) {
        var foundCity = cityService.findByName(cityname);
        return new ResponseEntity<>(foundCity, HttpStatus.OK);
    }
}

package es.nomann.tddproject.controller;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package es.nomann.tddproject.controller;

import es.nomann.tddproject.dto.Street;
import es.nomann.tddproject.dto.StreetReq;
import es.nomann.tddproject.repository.StreetRepository;
import es.nomann.tddproject.service.CityService;
import es.nomann.tddproject.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StreetController {

    private final StreetService streetService;
    private final CityService cityService;

    public StreetController(StreetService streetService, CityService cityService) {
        this.streetService = streetService;
        this.cityService = cityService;
    }

    @PostMapping("/street")
    public ResponseEntity<Street> addNewStreet(@RequestBody StreetReq streetReq) {
        var city = cityService.findByName(streetReq.getCityName());
        Street street = new Street();
        street.setName(streetReq.getStreetName());
        street.setCity(city);
        streetService.saveStreet(street);
        streetService.setToCity(street,city);
        return new ResponseEntity<>(street, HttpStatus.OK);
    }
}

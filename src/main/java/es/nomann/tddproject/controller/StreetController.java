package es.nomann.tddproject.controller;

import es.nomann.tddproject.dto.Street;
import es.nomann.tddproject.dto.StreetReq;
import es.nomann.tddproject.dto.StreetsReq;
import es.nomann.tddproject.service.CityService;
import es.nomann.tddproject.service.StreetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StreetController {

    private final StreetService streetService;
    private final CityService cityService;

    Logger logger = LoggerFactory.getLogger(StreetController.class);

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
        return ResponseEntity.ok(street);
    }

    @PostMapping("/streets")
    public ResponseEntity<List<String>> addNewStreets(@RequestBody StreetsReq streetsReq) {
        var res = streetsReq.getStreets();
        var city = cityService.findByName(streetsReq.getCityName());
        for(int i=0; i<streetsReq.getStreets().size(); i++) {
            Street street = new Street();
            street.setName(res.get(i));
            if (city != null) {
                logger.info("ok");
                street.setCity(city);
                streetService.saveStreet(street);
            }
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/streets")
    public ResponseEntity<List<Street>> getAllStreetsByCity(@RequestParam String city) {
        var result = streetService.findAllByCity(city);
        return ResponseEntity.ok(result);
    }

   /* @PostMapping("/street/neighbors")
    public ResponseEntity<Street> addNewStreetNeighbor(@RequestBody StreetReq streetReq) {
        var city = cityService.findByName(streetReq.getCityName());
        var strretstreetService.findStreetByName(streetReq.getStreetName());
        streetService.setNeighbors();
    }*/
}

package es.nomann.tddproject.service;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City findById(final Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City addCity(final City city) {
        return cityRepository.save(city);
    }
}

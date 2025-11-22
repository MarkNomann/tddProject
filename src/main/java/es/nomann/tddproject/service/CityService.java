package es.nomann.tddproject.service;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;

    }

    public City findById(final Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City addCity(final City city) {
        return cityRepository.save(city);
    }

    public void deleteCity(final City city) {
        cityRepository.delete(city);
    }

    public List<City> cities() {
        return cityRepository.findAll();
    }

    public City findByName(String name) {
        return cityRepository.findByName(name);
    }
}

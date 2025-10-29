package es.nomann.tddproject.service;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.dto.Street;
import es.nomann.tddproject.repository.StreetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StreetService {

    private final StreetRepository streetRepository;

    public StreetService(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    public Street getStreetById(Long id) {
        return  streetRepository.findById(id).orElse(null);
    }

    public Street saveStreet(Street street) {
        return streetRepository.save(street);
    }

    public Street findStreetByName(String name) {
        return streetRepository.findByName(name);
    }

    @Transactional
    public Street setToCity(Street street, City city) {
        street.setCity(city);
        return streetRepository.save(street);
    }


}

package es.nomann.tddproject.service;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.dto.Street;
import es.nomann.tddproject.repository.StreetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StreetService {

    @Autowired
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

    public void setNeighbor(Street street, Street neighbor) {
        var mainStreet = streetRepository.findByName(street.getName());
        var set = mainStreet.getNeighbors();
        if (set == null) {
            set = new HashSet<>();
        }
        set.add(neighbor);
        mainStreet.setNeighbors(set);
        streetRepository.save(mainStreet);
    }

    public List<Street> findAllByCity(String name) {
        return streetRepository.findAllByCity(name);
    }


}

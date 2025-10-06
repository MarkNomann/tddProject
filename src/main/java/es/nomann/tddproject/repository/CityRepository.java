package es.nomann.tddproject.repository;

import es.nomann.tddproject.dto.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}

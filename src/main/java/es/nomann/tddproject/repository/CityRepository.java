package es.nomann.tddproject.repository;

import es.nomann.tddproject.dto.City;
import es.nomann.tddproject.dto.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT city FROM City city WHERE city.name=:name")
    Street findByName(@Param("name") String name);
}

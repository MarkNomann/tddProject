package es.nomann.tddproject.repository;

import es.nomann.tddproject.dto.Street;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreetRepository extends CrudRepository<Street, Long> {

    @Query("SELECT street FROM Street street WHERE street.name=:name")
    Street findByName(@Param("name") String name);

    @Query("SELECT streets FROM Street streets WHERE streets.city.name=:name")
    List<Street> findAllByCity(@Param("name") String name);

}

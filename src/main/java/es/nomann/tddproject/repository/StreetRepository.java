package es.nomann.tddproject.repository;

import es.nomann.tddproject.dto.Street;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends CrudRepository<Street, Long> {

    @Query("SELECT streetName FROM Street streetName WHERE streetName.name=:name")
    Street findByName(@Param("name") String name);

}

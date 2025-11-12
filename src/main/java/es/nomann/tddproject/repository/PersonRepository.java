package es.nomann.tddproject.repository;

import es.nomann.tddproject.dto.Person;
import es.nomann.tddproject.dto.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT person FROM Person person WHERE person.username=:username")
    Person findByName(@Param("username") String username);
}

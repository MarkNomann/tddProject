package es.nomann.tddproject.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "city_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Person> people = new ArrayList<>();

    @OneToMany(mappedBy = "city_id", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Street> streets = new ArrayList<>();
    public City() {}

    public City(String name) {
        this.name = name;
        this.people = new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Person> getPeople() { return people; }

    public void setPeople(Person person) {
        people.add(person);
        person.setCity(this);
    }

    public List<Street> getStreets() { return streets; }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
        streets.forEach(street -> street.setCity(this));
    }

    public void removePerson(Person person) {
        people.remove(person);
    }
}

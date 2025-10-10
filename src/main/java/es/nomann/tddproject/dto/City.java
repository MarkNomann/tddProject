package es.nomann.tddproject.dto;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class City {

    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "city_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> people = new ArrayList<>();

    public City() {}

    public City(Long id, String name) {
        this.id = id;
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

    public void removePerson(Person person) {
        people.remove(person);
    }
}

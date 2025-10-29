package es.nomann.tddproject.dto;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city_id;
    @ManyToMany
    @JoinTable(
            name = "neighbors",
            joinColumns = @JoinColumn(name = "street_id"),
            inverseJoinColumns = @JoinColumn(name = "neighbor_id")
    )
    private Set<Street> neighbors;

    public Street() {}

    public Street(final String name, final City city_id, Set<Street> neighbors) {
        this.name = name;
        this.city_id = city_id;
        this.neighbors = neighbors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city_id;
    }

    public void setCity(City city) {
        this.city_id = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Street> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Set<Street> neighbors) {
        this.neighbors = neighbors;
    }
}

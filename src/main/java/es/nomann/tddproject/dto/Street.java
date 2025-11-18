package es.nomann.tddproject.dto;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "city")
    private City city;
    @ManyToMany
    @JoinTable(
            name = "neighbors",
            joinColumns = @JoinColumn(name = "street_id"),
            inverseJoinColumns = @JoinColumn(name = "neighbor_id")
    )
    private Set<Street> neighbors;

    public Street() {}

    public Street(final String name, final City city, Set<Street> neighbors) {
        this.name = name;
        this.city = city;
        this.neighbors = neighbors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

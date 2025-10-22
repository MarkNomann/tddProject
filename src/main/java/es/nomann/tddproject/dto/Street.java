package es.nomann.tddproject.dto;

import jakarta.persistence.*;

@Entity
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city_id;

    public Street() {
    }

    public Street(final String name, final City city_id) {
        this.name = name;
        this.city_id = city_id;
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

    public void setCity(City city) {this.name = name;
        this.city_id = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

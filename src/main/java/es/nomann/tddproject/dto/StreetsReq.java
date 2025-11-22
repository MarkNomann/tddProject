package es.nomann.tddproject.dto;

import java.util.List;

public class StreetsReq {

    private String cityName;
    private List<String> streets;

    public StreetsReq() {}

    public StreetsReq(final List<String> streets) {
        this.streets = streets;
    }

    public String getCityName() {
        return cityName;
    }

    public List<String> getStreets() {
        return streets;
    }

    public void setStreets(final List<String> streets) {
        this.streets = streets;
    }
}

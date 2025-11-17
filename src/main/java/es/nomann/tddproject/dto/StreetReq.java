package es.nomann.tddproject.dto;

public class StreetReq {
    private String streetName;
    private String cityName;

    public StreetReq() {}

    public StreetReq(final String streetName, final String cityName) {
        this.streetName = streetName;
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }
    public String getCityName() {
        return cityName;
    }
}

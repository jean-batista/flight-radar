package com.flightradarmsn.flightradar.model.dto.request;

import java.util.Objects;

public class SearchFlightDTO {

    private Long id;
    private String origin;
    private String destiny;
    private String airline;

    public SearchFlightDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SearchFlightDTO that = (SearchFlightDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(origin, that.origin) && Objects.equals(destiny, that.destiny) && Objects.equals(airline, that.airline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origin, destiny, airline);
    }
}

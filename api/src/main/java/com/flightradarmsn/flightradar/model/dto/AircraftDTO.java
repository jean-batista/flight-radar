package com.flightradarmsn.flightradar.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class AircraftDTO implements Serializable {

    private String name;
    private String registration;
    private String iata;
    private String icao;
    private String icao24;

    @JsonProperty(value = "aircraft_category")
    private String aircraftCategory;

    @JsonProperty(value = "image_url")
    private String imageUrl;

    public AircraftDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public String getAircraftCategory() {
        return aircraftCategory;
    }

    public void setAircraftCategory(String aircraftCategory) {
        this.aircraftCategory = aircraftCategory;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AircraftDTO that = (AircraftDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(registration, that.registration) && Objects.equals(iata, that.iata) && Objects.equals(icao, that.icao) && Objects.equals(icao24, that.icao24) && Objects.equals(aircraftCategory, that.aircraftCategory) && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, registration, iata, icao, icao24, aircraftCategory, imageUrl);
    }
}

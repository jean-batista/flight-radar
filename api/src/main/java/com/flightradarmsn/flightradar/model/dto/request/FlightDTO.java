package com.flightradarmsn.flightradar.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class FlightDTO implements Serializable {

    private Integer number;
    private String iata;
    private String icao;
    private String codeshared;

    @JsonProperty(value = "cruise_altitude")
    private Integer cruiseAltitude;

    public FlightDTO() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public String getCodeshared() {
        return codeshared;
    }

    public void setCodeshared(String codeshared) {
        this.codeshared = codeshared;
    }

    public Integer getCruiseAltitude() {
        return cruiseAltitude;
    }

    public void setCruiseAltitude(Integer cruiseAltitude) {
        this.cruiseAltitude = cruiseAltitude;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FlightDTO flightDTO = (FlightDTO) o;
        return Objects.equals(number, flightDTO.number) && Objects.equals(iata, flightDTO.iata) && Objects.equals(icao, flightDTO.icao) && Objects.equals(codeshared, flightDTO.codeshared) && Objects.equals(cruiseAltitude, flightDTO.cruiseAltitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, iata, icao, codeshared, cruiseAltitude);
    }
}

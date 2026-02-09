package com.flightradarmsn.flightradar.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class FlightResponseDTO implements Serializable {

    private Integer number;
    private String iata;
    private String icao;
    private String codeshared;

    @JsonProperty(value = "cruise_altitude")
    private Integer cruiseAltitude;

    public FlightResponseDTO() {
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
        FlightResponseDTO flightResponseDTO = (FlightResponseDTO) o;
        return Objects.equals(number, flightResponseDTO.number) && Objects.equals(iata, flightResponseDTO.iata) && Objects.equals(icao, flightResponseDTO.icao) && Objects.equals(codeshared, flightResponseDTO.codeshared) && Objects.equals(cruiseAltitude, flightResponseDTO.cruiseAltitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, iata, icao, codeshared, cruiseAltitude);
    }
}

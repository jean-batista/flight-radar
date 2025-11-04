package com.flightradarmsn.flightradar.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Flight implements Serializable {

    private Integer number;
    private String iata;
    private String icao;
    private String codeshared;
    private Integer cruiseAltitude;

    public Flight() {
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
        Flight flight = (Flight) o;
        return Objects.equals(number, flight.number) && Objects.equals(iata, flight.iata) && Objects.equals(icao, flight.icao) && Objects.equals(codeshared, flight.codeshared) && Objects.equals(cruiseAltitude, flight.cruiseAltitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, iata, icao, codeshared, cruiseAltitude);
    }
}

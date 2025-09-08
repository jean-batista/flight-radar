package com.flightradarmsn.flightradar.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

public class AirlineDTO implements Serializable {

    private String name;
    private String iata;
    private String icao;

    public AirlineDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AirlineDTO that = (AirlineDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(iata, that.iata) && Objects.equals(icao, that.icao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, iata, icao);
    }
}

package com.flightradarmsn.flightradar.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class AircraftDTO implements Serializable {

    private String registration;
    private String iata;
    private String icao;
    private String icao24;

    public AircraftDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AircraftDTO that = (AircraftDTO) o;
        return Objects.equals(registration, that.registration) && Objects.equals(iata, that.iata) && Objects.equals(icao, that.icao) && Objects.equals(icao24, that.icao24);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registration, iata, icao, icao24);
    }
}

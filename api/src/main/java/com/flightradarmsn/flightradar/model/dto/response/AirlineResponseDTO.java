package com.flightradarmsn.flightradar.model.dto.response;

import java.io.Serializable;
import java.util.Objects;

public class AirlineResponseDTO implements Serializable {

    private Long id;
    private String name;
    private String iata;
    private String icao;

    public AirlineResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        AirlineResponseDTO that = (AirlineResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(iata, that.iata) && Objects.equals(icao, that.icao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, iata, icao);
    }
}

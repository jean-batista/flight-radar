package com.flightradarmsn.flightradar.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

@Embeddable
public class Departure implements Serializable {

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport airport ;

    private String timezone;
    private String iata;
    private String icao;
    private String terminal;
    private String gate;
    private OffsetDateTime scheduled;
    private OffsetDateTime estimated;
    private OffsetDateTime actual;
    private OffsetDateTime estimatedRunway;
    private OffsetDateTime actualRunway;

    public Departure() {
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
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

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public OffsetDateTime getScheduled() {
        return scheduled;
    }

    public void setScheduled(OffsetDateTime scheduled) {
        this.scheduled = scheduled;
    }

    public OffsetDateTime getEstimated() {
        return estimated;
    }

    public void setEstimated(OffsetDateTime estimated) {
        this.estimated = estimated;
    }

    public OffsetDateTime getActual() {
        return actual;
    }

    public void setActual(OffsetDateTime actual) {
        this.actual = actual;
    }

    public OffsetDateTime getEstimatedRunway() {
        return estimatedRunway;
    }

    public void setEstimatedRunway(OffsetDateTime estimatedRunway) {
        this.estimatedRunway = estimatedRunway;
    }

    public OffsetDateTime getActualRunway() {
        return actualRunway;
    }

    public void setActualRunway(OffsetDateTime actualRunway) {
        this.actualRunway = actualRunway;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Departure departure = (Departure) o;
        return Objects.equals(airport, departure.airport) && Objects.equals(timezone, departure.timezone) && Objects.equals(iata, departure.iata) && Objects.equals(icao, departure.icao) && Objects.equals(terminal, departure.terminal) && Objects.equals(gate, departure.gate) && Objects.equals(scheduled, departure.scheduled) && Objects.equals(estimated, departure.estimated) && Objects.equals(actual, departure.actual) && Objects.equals(estimatedRunway, departure.estimatedRunway) && Objects.equals(actualRunway, departure.actualRunway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airport, timezone, iata, icao, terminal, gate, scheduled, estimated, actual, estimatedRunway, actualRunway);
    }
}

package com.flightradarmsn.flightradar.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

@Embeddable
public class Arrival implements Serializable {

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport airport ;

    private String timezone;
    private String iata;
    private String icao;
    private String terminal;
    private String gate;
    private String baggage;
    private OffsetDateTime scheduled;
    private OffsetDateTime estimated;
    private OffsetDateTime actual;
    private OffsetDateTime estimatedRunway;
    private OffsetDateTime actualRunway;

    public Arrival() {
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

    public String getBaggage() {
        return baggage;
    }

    public void setBaggage(String baggage) {
        this.baggage = baggage;
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
        Arrival arrival = (Arrival) o;
        return Objects.equals(airport, arrival.airport) && Objects.equals(timezone, arrival.timezone) && Objects.equals(iata, arrival.iata) && Objects.equals(icao, arrival.icao) && Objects.equals(terminal, arrival.terminal) && Objects.equals(gate, arrival.gate) && Objects.equals(baggage, arrival.baggage) && Objects.equals(scheduled, arrival.scheduled) && Objects.equals(estimated, arrival.estimated) && Objects.equals(actual, arrival.actual) && Objects.equals(estimatedRunway, arrival.estimatedRunway) && Objects.equals(actualRunway, arrival.actualRunway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airport, timezone, iata, icao, terminal, gate, baggage, scheduled, estimated, actual, estimatedRunway, actualRunway);
    }
}

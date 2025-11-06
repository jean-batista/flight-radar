package com.flightradarmsn.flightradar.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

public class ArrivalDTO implements Serializable {

    private AirportDTO airport ;
    private String timezone;
    private String iata;
    private String icao;
    private String terminal;
    private String gate;
    private String baggage;
    private OffsetDateTime scheduled;
    private OffsetDateTime estimated;
    private OffsetDateTime actual;

    @JsonProperty(value = "estimated_runway")
    private OffsetDateTime estimatedRunway;

    @JsonProperty(value = "actual_runway")
    private OffsetDateTime actualRunway;

    public ArrivalDTO() {
    }

    public AirportDTO getAirport() {
        return airport;
    }

    public void setAirport(AirportDTO airport) {
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
        ArrivalDTO that = (ArrivalDTO) o;
        return Objects.equals(airport, that.airport) && Objects.equals(timezone, that.timezone) && Objects.equals(iata, that.iata) && Objects.equals(icao, that.icao) && Objects.equals(terminal, that.terminal) && Objects.equals(gate, that.gate) && Objects.equals(baggage, that.baggage) && Objects.equals(scheduled, that.scheduled) && Objects.equals(estimated, that.estimated) && Objects.equals(actual, that.actual) && Objects.equals(estimatedRunway, that.estimatedRunway) && Objects.equals(actualRunway, that.actualRunway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airport, timezone, iata, icao, terminal, gate, baggage, scheduled, estimated, actual, estimatedRunway, actualRunway);
    }
}

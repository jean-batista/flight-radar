package com.flightradarmsn.flightradar.model.dto;

import java.util.Objects;

public class FlightPlanMinDTO {

    private FlightDTO flight;
    private AircraftDTO aircraft;
    private LiveDTO live;

    public FlightPlanMinDTO() {
    }

    public FlightDTO getFlight() {
        return flight;
    }

    public void setFlight(FlightDTO flight) {
        this.flight = flight;
    }

    public AircraftDTO getAircraft() {
        return aircraft;
    }

    public void setAircraft(AircraftDTO aircraft) {
        this.aircraft = aircraft;
    }

    public LiveDTO getLive() {
        return live;
    }

    public void setLive(LiveDTO live) {
        this.live = live;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FlightPlanMinDTO that = (FlightPlanMinDTO) o;
        return Objects.equals(flight, that.flight) && Objects.equals(aircraft, that.aircraft) && Objects.equals(live, that.live);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flight, aircraft, live);
    }
}

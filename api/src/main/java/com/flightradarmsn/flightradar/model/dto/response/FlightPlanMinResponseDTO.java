package com.flightradarmsn.flightradar.model.dto.response;

import java.util.Objects;

public class FlightPlanMinResponseDTO {

    private FlightResponseDTO flight;
    private AircraftResponseDTO aircraft;
    private LiveResponseDTO live;

    public FlightPlanMinResponseDTO() {
    }

    public FlightResponseDTO getFlight() {
        return flight;
    }

    public void setFlight(FlightResponseDTO flight) {
        this.flight = flight;
    }

    public AircraftResponseDTO getAircraft() {
        return aircraft;
    }

    public void setAircraft(AircraftResponseDTO aircraft) {
        this.aircraft = aircraft;
    }

    public LiveResponseDTO getLive() {
        return live;
    }

    public void setLive(LiveResponseDTO live) {
        this.live = live;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FlightPlanMinResponseDTO that = (FlightPlanMinResponseDTO) o;
        return Objects.equals(flight, that.flight) && Objects.equals(aircraft, that.aircraft) && Objects.equals(live, that.live);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flight, aircraft, live);
    }
}

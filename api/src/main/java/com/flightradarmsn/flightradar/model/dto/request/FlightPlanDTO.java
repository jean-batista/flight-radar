package com.flightradarmsn.flightradar.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightradarmsn.flightradar.model.enums.FlightPhase;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class FlightPlanDTO implements Serializable {

    private Long id;

    @JsonProperty(value = "flight_date")
    private LocalDate flightDate;

    @JsonProperty(value = "flight_phase")
    private FlightPhase flightPhase;

    private DepartureDTO departure;
    private ArrivalDTO arrival;
    private Long airlineId;
    private FlightDTO flight;
    private Long aircraftId;
    private Long routeId;
    private LiveDTO live;

    public FlightPlanDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public FlightPhase getFlightPhase() {
        return flightPhase;
    }

    public void setFlightPhase(FlightPhase flightPhase) {
        this.flightPhase = flightPhase;
    }

    public DepartureDTO getDeparture() {
        return departure;
    }

    public void setDeparture(DepartureDTO departure) {
        this.departure = departure;
    }

    public ArrivalDTO getArrival() {
        return arrival;
    }

    public void setArrival(ArrivalDTO arrival) {
        this.arrival = arrival;
    }

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public FlightDTO getFlight() {
        return flight;
    }

    public void setFlight(FlightDTO flight) {
        this.flight = flight;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
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
        FlightPlanDTO that = (FlightPlanDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(flightDate, that.flightDate) && flightPhase == that.flightPhase && Objects.equals(departure, that.departure) && Objects.equals(arrival, that.arrival) && Objects.equals(airlineId, that.airlineId) && Objects.equals(flight, that.flight) && Objects.equals(aircraftId, that.aircraftId) && Objects.equals(routeId, that.routeId) && Objects.equals(live, that.live);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightDate, flightPhase, departure, arrival, airlineId, flight, aircraftId, routeId, live);
    }
}

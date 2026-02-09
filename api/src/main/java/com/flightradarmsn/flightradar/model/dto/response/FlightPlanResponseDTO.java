package com.flightradarmsn.flightradar.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightradarmsn.flightradar.model.enums.FlightPhase;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class FlightPlanResponseDTO implements Serializable {

    private Long id;

    @JsonProperty(value = "flight_date")
    private LocalDate flightDate;

    @JsonProperty(value = "flight_phase")
    private FlightPhase flightPhase;

    private DepartureResponseDTO departure;
    private ArrivalResponseDTO arrival;
    private AirlineResponseDTO airline;
    private FlightResponseDTO flight;
    private AircraftResponseDTO aircraft;
    private RouteMinResponseDTO route;
    private LiveResponseDTO live;

    public FlightPlanResponseDTO() {
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

    public DepartureResponseDTO getDeparture() {
        return departure;
    }

    public void setDeparture(DepartureResponseDTO departure) {
        this.departure = departure;
    }

    public ArrivalResponseDTO getArrival() {
        return arrival;
    }

    public void setArrival(ArrivalResponseDTO arrival) {
        this.arrival = arrival;
    }

    public AirlineResponseDTO getAirline() {
        return airline;
    }

    public void setAirline(AirlineResponseDTO airline) {
        this.airline = airline;
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

    public RouteMinResponseDTO getRoute() {
        return route;
    }

    public void setRoute(RouteMinResponseDTO route) {
        this.route = route;
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
        FlightPlanResponseDTO that = (FlightPlanResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(flightDate, that.flightDate) && Objects.equals(flightPhase, that.flightPhase) && Objects.equals(departure, that.departure) && Objects.equals(arrival, that.arrival) && Objects.equals(airline, that.airline) && Objects.equals(flight, that.flight) && Objects.equals(aircraft, that.aircraft) && Objects.equals(route, that.route) && Objects.equals(live, that.live);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightDate, flightPhase, departure, arrival, airline, flight, aircraft, route, live);
    }
}

package com.flightradarmsn.flightradar.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class FlightPlanDTO implements Serializable {

    private Long id;

    @JsonProperty(value = "flight_date")
    private LocalDate flightDate;

    @JsonProperty(value = "flight_status")
    private String flightStatus;

    private DepartureDTO departure;
    private ArrivalDTO arrival;
    private AirlineDTO airline;
    private FlightDTO flight;
    private AircraftDTO aircraft;
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

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
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

    public AirlineDTO getAirline() {
        return airline;
    }

    public void setAirline(AirlineDTO airline) {
        this.airline = airline;
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
        FlightPlanDTO that = (FlightPlanDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(flightDate, that.flightDate) && Objects.equals(flightStatus, that.flightStatus) && Objects.equals(departure, that.departure) && Objects.equals(arrival, that.arrival) && Objects.equals(airline, that.airline) && Objects.equals(flight, that.flight) && Objects.equals(aircraft, that.aircraft) && Objects.equals(live, that.live);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightDate, flightStatus, departure, arrival, airline, flight, aircraft, live);
    }
}

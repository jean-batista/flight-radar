package com.flightradarmsn.flightradar.model.entities;

import com.flightradarmsn.flightradar.model.enums.FlightPhase;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_flight_plan")
public class FlightPlan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate flightDate;
    private FlightPhase flightPhase;

    @Embedded
    @Column(length = 1024)
    @AttributeOverrides({
            @AttributeOverride(name = "timezone", column = @Column(name = "departure_timezone")),
            @AttributeOverride(name = "iata", column = @Column(name = "departure_iata")),
            @AttributeOverride(name = "icao", column = @Column(name = "departure_icao")),
            @AttributeOverride(name = "terminal", column = @Column(name = "departure_terminal")),
            @AttributeOverride(name = "gate", column = @Column(name = "departure_gate")),
            @AttributeOverride(name = "delay", column = @Column(name = "departure_delay")),
            @AttributeOverride(name = "scheduled", column = @Column(name = "departure_scheduled")),
            @AttributeOverride(name = "estimated", column = @Column(name = "departure_estimated")),
            @AttributeOverride(name = "actual", column = @Column(name = "departure_actual")),
            @AttributeOverride(name = "estimatedRunway", column = @Column(name = "departure_estimated_runway")),
            @AttributeOverride(name = "actualRunway", column = @Column(name = "departure_actual_runway"))
    })
    private Departure departure;

    @Embedded
    @Column(length = 1024)
    @AttributeOverrides({
            @AttributeOverride(name = "timezone", column = @Column(name = "arrival_timezone")),
            @AttributeOverride(name = "iata", column = @Column(name = "arrival_iata")),
            @AttributeOverride(name = "icao", column = @Column(name = "arrival_icao")),
            @AttributeOverride(name = "terminal", column = @Column(name = "arrival_terminal")),
            @AttributeOverride(name = "gate", column = @Column(name = "arrival_gate")),
            @AttributeOverride(name = "baggage", column = @Column(name = "arrival_baggage")),
            @AttributeOverride(name = "delay", column = @Column(name = "arrival_delay")),
            @AttributeOverride(name = "scheduled", column = @Column(name = "arrival_scheduled")),
            @AttributeOverride(name = "estimated", column = @Column(name = "arrival_estimated")),
            @AttributeOverride(name = "actual", column = @Column(name = "arrival_actual")),
            @AttributeOverride(name = "estimatedRunway", column = @Column(name = "arrival_estimated_runway")),
            @AttributeOverride(name = "actualRunway", column = @Column(name = "arrival_actual_runway"))
    })
    private Arrival arrival;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @Embedded
    @Column(length = 512)
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "flight_number")),
            @AttributeOverride(name = "iata", column = @Column(name = "flight_iata")),
            @AttributeOverride(name = "icao", column = @Column(name = "flight_icao")),
            @AttributeOverride(name = "codeshared", column = @Column(name = "flight_codeshared")),
            @AttributeOverride(name = "cruiseAltitude", column = @Column(name = "flight_cruise_altitude"))
    })
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @Embedded
    @Column(length = 1024)
    @AttributeOverrides({
            @AttributeOverride(name = "updated", column = @Column(name = "live_updated")),
            @AttributeOverride(name = "latitude", column = @Column(name = "live_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "live_longitude")),
            @AttributeOverride(name = "altitude", column = @Column(name = "live_altitude")),
            @AttributeOverride(name = "direction", column = @Column(name = "live_direction")),
            @AttributeOverride(name = "speedHorizontal", column = @Column(name = "live_speed_horizontal")),
            @AttributeOverride(name = "speedVertical", column = @Column(name = "live_speed_vertical")),
            @AttributeOverride(name = "progress", column = @Column(name = "live_progress")),
            @AttributeOverride(name = "isGround", column = @Column(name = "live_is_ground"))
    })
    private Live live;

    public FlightPlan() {
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

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Live getLive() {
        return live;
    }

    public void setLive(Live live) {
        this.live = live;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FlightPlan that = (FlightPlan) o;
        return Objects.equals(id, that.id) && Objects.equals(flightDate, that.flightDate) && Objects.equals(flightPhase, that.flightPhase) && Objects.equals(departure, that.departure) && Objects.equals(arrival, that.arrival) && Objects.equals(airline, that.airline) && Objects.equals(flight, that.flight) && Objects.equals(aircraft, that.aircraft) && Objects.equals(route, that.route) && Objects.equals(live, that.live);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightDate, flightPhase, departure, arrival, airline, flight, aircraft, route, live);
    }
}

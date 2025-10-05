package com.flightradarmsn.flightradar.model.dto;

import com.flightradarmsn.flightradar.model.entities.Airport;

import java.util.List;
import java.util.Objects;

public class RouteDTO {

    private Long id;
    private String name;
    private Airport origin;
    private Airport destination;
    private List<CoordinatesDTO> waypoints;

    public RouteDTO() {
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

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public List<CoordinatesDTO> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<CoordinatesDTO> waypoints) {
        this.waypoints = waypoints;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RouteDTO routeDTO = (RouteDTO) o;
        return Objects.equals(id, routeDTO.id) && Objects.equals(name, routeDTO.name) && Objects.equals(origin, routeDTO.origin) && Objects.equals(destination, routeDTO.destination) && Objects.equals(waypoints, routeDTO.waypoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, origin, destination, waypoints);
    }
}

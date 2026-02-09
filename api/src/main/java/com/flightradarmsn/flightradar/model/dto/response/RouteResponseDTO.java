package com.flightradarmsn.flightradar.model.dto.response;

import com.flightradarmsn.flightradar.model.entities.Airport;

import java.util.List;
import java.util.Objects;

public class RouteResponseDTO {

    private Long id;
    private String name;
    private Airport origin;
    private Airport destination;
    private List<CoordinatesResponseDTO> waypoints;

    public RouteResponseDTO() {
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

    public List<CoordinatesResponseDTO> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<CoordinatesResponseDTO> waypoints) {
        this.waypoints = waypoints;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RouteResponseDTO routeResponseDTO = (RouteResponseDTO) o;
        return Objects.equals(id, routeResponseDTO.id) && Objects.equals(name, routeResponseDTO.name) && Objects.equals(origin, routeResponseDTO.origin) && Objects.equals(destination, routeResponseDTO.destination) && Objects.equals(waypoints, routeResponseDTO.waypoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, origin, destination, waypoints);
    }
}

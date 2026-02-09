package com.flightradarmsn.flightradar.model.dto.request;

import com.flightradarmsn.flightradar.model.dto.response.CoordinatesResponseDTO;
import com.flightradarmsn.flightradar.model.entities.Airport;

import java.util.List;
import java.util.Objects;

public class RouteDTO {

    private String name;
    private Airport origin;
    private Airport destination;
    private List<CoordinatesResponseDTO> waypoints;

    public RouteDTO() {
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
        RouteDTO that = (RouteDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(origin, that.origin) && Objects.equals(destination, that.destination) && Objects.equals(waypoints, that.waypoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, origin, destination, waypoints);
    }
}

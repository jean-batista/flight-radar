package com.flightradarmsn.flightradar.model.dto;

import java.util.List;
import java.util.Objects;

public class RouteMinDTO {

    private Long id;
    private String name;
    private List<CoordinatesDTO> waypoints;

    public RouteMinDTO() {
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

    public List<CoordinatesDTO> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<CoordinatesDTO> waypoints) {
        this.waypoints = waypoints;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RouteMinDTO that = (RouteMinDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(waypoints, that.waypoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, waypoints);
    }
}

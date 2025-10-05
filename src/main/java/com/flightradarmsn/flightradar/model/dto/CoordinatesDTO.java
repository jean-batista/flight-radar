package com.flightradarmsn.flightradar.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class CoordinatesDTO implements Serializable {

    private Double latitude;
    private Double longitude;
    private Double direction;

    public CoordinatesDTO() {
    }

    public CoordinatesDTO(Double latitude, Double longitude, Double direction) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDirection() {
        return direction;
    }

    public void setDirection(Double direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatesDTO that = (CoordinatesDTO) o;
        return Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(direction, that.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, direction);
    }
}

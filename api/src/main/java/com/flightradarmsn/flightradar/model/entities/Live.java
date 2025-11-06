package com.flightradarmsn.flightradar.model.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class Live implements Serializable {

    private LocalDateTime updated;
    private Double latitude;
    private Double longitude;
    private Integer altitude;
    private Integer direction;
    private Integer speedHorizontal;
    private Integer speedVertical;
    private Integer progress;
    private Boolean isGround;

    public Live() {
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
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

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getSpeedHorizontal() {
        return speedHorizontal;
    }

    public void setSpeedHorizontal(Integer speedHorizontal) {
        this.speedHorizontal = speedHorizontal;
    }

    public Integer getSpeedVertical() {
        return speedVertical;
    }

    public void setSpeedVertical(Integer speedVertical) {
        this.speedVertical = speedVertical;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Boolean getGround() {
        return isGround;
    }

    public void setGround(Boolean ground) {
        isGround = ground;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Live live = (Live) o;
        return Objects.equals(updated, live.updated) && Objects.equals(latitude, live.latitude) && Objects.equals(longitude, live.longitude) && Objects.equals(altitude, live.altitude) && Objects.equals(direction, live.direction) && Objects.equals(speedHorizontal, live.speedHorizontal) && Objects.equals(speedVertical, live.speedVertical) && Objects.equals(progress, live.progress) && Objects.equals(isGround, live.isGround);
    }

    @Override
    public int hashCode() {
        return Objects.hash(updated, latitude, longitude, altitude, direction, speedHorizontal, speedVertical, progress, isGround);
    }
}

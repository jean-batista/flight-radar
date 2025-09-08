package com.flightradarmsn.flightradar.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
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
    private Boolean isGround;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tb_trail_points",
            joinColumns = @JoinColumn(name = "flight_plan_id")
    )
    private List<Trail> trail;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tb_predicted_trail_points",
            joinColumns = @JoinColumn(name = "flight_plan_id")
    )
    private List<Trail> predictedTrail;

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

    public Boolean getGround() {
        return isGround;
    }

    public void setGround(Boolean ground) {
        isGround = ground;
    }

    public List<Trail> getTrail() {
        return trail;
    }

    public void setTrail(List<Trail> trail) {
        this.trail = trail;
    }

    public List<Trail> getPredictedTrail() {
        return predictedTrail;
    }

    public void setPredictedTrail(List<Trail> predictedTrail) {
        this.predictedTrail = predictedTrail;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Live live = (Live) o;
        return Objects.equals(updated, live.updated) && Objects.equals(latitude, live.latitude) && Objects.equals(longitude, live.longitude) && Objects.equals(altitude, live.altitude) && Objects.equals(direction, live.direction) && Objects.equals(speedHorizontal, live.speedHorizontal) && Objects.equals(speedVertical, live.speedVertical) && Objects.equals(isGround, live.isGround) && Objects.equals(trail, live.trail) && Objects.equals(predictedTrail, live.predictedTrail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(updated, latitude, longitude, altitude, direction, speedHorizontal, speedVertical, isGround, trail, predictedTrail);
    }
}

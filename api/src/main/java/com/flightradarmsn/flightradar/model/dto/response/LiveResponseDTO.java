package com.flightradarmsn.flightradar.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class LiveResponseDTO implements Serializable {

    private LocalDateTime updated;
    private Double latitude;
    private Double longitude;
    private Integer altitude;
    private Integer direction;

    @JsonProperty(value = "speed_horizontal")
    private Integer speedHorizontal;

    @JsonProperty(value = "speed_vertical")
    private Integer speedVertical;

    private Integer progress;

    @JsonProperty(value = "is_ground")
    private Boolean isGround;

    public LiveResponseDTO() {
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
        LiveResponseDTO liveResponseDTO = (LiveResponseDTO) o;
        return Objects.equals(updated, liveResponseDTO.updated) && Objects.equals(latitude, liveResponseDTO.latitude) && Objects.equals(longitude, liveResponseDTO.longitude) && Objects.equals(altitude, liveResponseDTO.altitude) && Objects.equals(direction, liveResponseDTO.direction) && Objects.equals(speedHorizontal, liveResponseDTO.speedHorizontal) && Objects.equals(speedVertical, liveResponseDTO.speedVertical) && Objects.equals(progress, liveResponseDTO.progress) && Objects.equals(isGround, liveResponseDTO.isGround);
    }

    @Override
    public int hashCode() {
        return Objects.hash(updated, latitude, longitude, altitude, direction, speedHorizontal, speedVertical, progress, isGround);
    }
}

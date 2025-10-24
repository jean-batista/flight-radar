package com.flightradarmsn.flightradar.simulation.state;

import com.flightradarmsn.flightradar.model.dto.CoordinatesDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightState {

    // ID do plano de voo
    private Long flightPlanId;

    // Numero do voo
    private Integer flightNumber;

    // Altitude
    private Integer altitude; // TODO

    // Direção
    private Integer direction; // TODO

    // Horizontal speed
    private Integer speedHorizontal;

    // Vertical Speed
    private Integer speedVertical; // TODO

    // Lista de waypoints
    private List<CoordinatesDTO> waypoints;

    // Waypoint atual
    private CoordinatesDTO currentPosition;

    // Waypoints já percorridos
    private List<CoordinatesDTO> trail = new ArrayList<>();

    // Índice do próximo waypoint
    private int nextWaypointIndex;

    // Progresso
    private Integer progress;

    public FlightState() {
    }

    public FlightState(
            Long flightPlanId,
            Integer flightNumber,
            Integer altitude,
            Integer direction,
            Integer speedHorizontal,
            Integer speedVertical,
            List<CoordinatesDTO> waypoints) {
        this.flightPlanId = flightPlanId;
        this.flightNumber = flightNumber;
        this.altitude = altitude;
        this.direction = direction;
        this.speedHorizontal = speedHorizontal;
        this.speedVertical = speedVertical;
        this.waypoints = waypoints;
        // A simulação começa no primeiro ponto da rota
        this.currentPosition = waypoints.getFirst();
        for(int i = 0; i <= waypoints.indexOf(currentPosition); i++) {
            this.trail.add(waypoints.get(i));
        }

        // O alvo inicial é o segundo ponto (índice 1)
        this.nextWaypointIndex = 1;
        this.progress = (nextWaypointIndex * 100) / waypoints.size();
    }

    public Long getFlightPlanId() {
        return flightPlanId;
    }

    public void setFlightPlanId(Long flightPlanId) {
        this.flightPlanId = flightPlanId;
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
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

    public List<CoordinatesDTO> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<CoordinatesDTO> waypoints) {
        this.waypoints = waypoints;
    }

    public CoordinatesDTO getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(CoordinatesDTO currentPosition) {
        this.currentPosition = currentPosition;
    }

    public List<CoordinatesDTO> getTrail() {
        return trail;
    }

    public void setTrail(List<CoordinatesDTO> trail) {
        this.trail = trail;
    }

    public int getNextWaypointIndex() {
        return nextWaypointIndex;
    }

    public void setNextWaypointIndex(int nextWaypointIndex) {
        this.nextWaypointIndex = nextWaypointIndex;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    // Verifica se o voo já completou a rota
    public boolean hasFinished() {
        return nextWaypointIndex >= waypoints.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FlightState that = (FlightState) o;
        return nextWaypointIndex == that.nextWaypointIndex && Objects.equals(flightPlanId, that.flightPlanId) && Objects.equals(flightNumber, that.flightNumber) && Objects.equals(altitude, that.altitude) && Objects.equals(direction, that.direction) && Objects.equals(speedHorizontal, that.speedHorizontal) && Objects.equals(speedVertical, that.speedVertical) && Objects.equals(waypoints, that.waypoints) && Objects.equals(currentPosition, that.currentPosition) && Objects.equals(trail, that.trail) && Objects.equals(progress, that.progress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightPlanId, flightNumber, altitude, direction, speedHorizontal, speedVertical, waypoints, currentPosition, trail, nextWaypointIndex, progress);
    }
}

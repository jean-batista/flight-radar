package com.flightradarmsn.flightradar.simulation.state;

import com.flightradarmsn.flightradar.model.dto.CoordinatesDTO;
import com.flightradarmsn.flightradar.model.enums.FlightPhase;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlightState {

    // ID do plano de voo
    private Long flightPlanId;

    // Numero do voo
    private Integer flightNumber;

    // Altitude de cruzeiro
    private Integer cruiseAltitude;

    // Altitude
    private Integer currentAltitude;

    // Horario de decolagem
    private OffsetDateTime scheduled;

    // Fase de voo
    private FlightPhase phase;

    // Direção
    private Integer direction;

    // Horizontal speed
    private Integer speedHorizontal;

    // Vertical Speed
    private Integer speedVertical;

    // Lista de waypoints
    private List<CoordinatesDTO> waypoints;

    // Waypoint atual
    private CoordinatesDTO currentPosition;

    // Waypoints ja percorridos
    private List<CoordinatesDTO> trail = new ArrayList<>();

    // Indice do proximo waypoint
    private int nextWaypointIndex;

    // Progresso
    private Integer progress;

    // Construtor
    public FlightState(
            Long flightPlanId,
            Integer flightNumber,
            Integer cruiseAltitude,
            Integer currentAltitude,
            OffsetDateTime scheduled,
            FlightPhase phase,
            Integer direction,
            Integer speedHorizontal,
            Integer speedVertical,
            List<CoordinatesDTO> waypoints) {
        this.flightPlanId = flightPlanId;
        this.flightNumber = flightNumber;
        this.cruiseAltitude = cruiseAltitude;
        this.currentAltitude = currentAltitude;
        this.scheduled = scheduled;
        this.phase = phase;
        this.direction = direction;
        this.speedHorizontal = speedHorizontal;
        this.speedVertical = speedVertical;
        this.waypoints = waypoints;
        // A simulacao comeca no primeiro ponto da rota
        this.currentPosition = waypoints.getFirst();
        for(int i = 0; i <= waypoints.indexOf(currentPosition); i++) {
            this.trail.add(waypoints.get(i));
        }

        // O alvo inicial e o segundo ponto (indice 1)
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

    public Integer getCruiseAltitude() {
        return cruiseAltitude;
    }

    public void setCruiseAltitude(Integer cruiseAltitude) {
        this.cruiseAltitude = cruiseAltitude;
    }

    public Integer getCurrentAltitude() {
        return currentAltitude;
    }

    public void setCurrentAltitude(Integer currentAltitude) {
        this.currentAltitude = currentAltitude;
    }

    public OffsetDateTime getScheduled() {
        return scheduled;
    }

    public void setScheduled(OffsetDateTime scheduled) {
        this.scheduled = scheduled;
    }

    public FlightPhase getPhase() {
        return phase;
    }

    public void setPhase(FlightPhase phase) {
        this.phase = phase;
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

    // Verifica se o voo ja completou a rota
    public boolean hasFinished() {
        return nextWaypointIndex >= waypoints.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FlightState that = (FlightState) o;
        return nextWaypointIndex == that.nextWaypointIndex && Objects.equals(flightPlanId, that.flightPlanId) && Objects.equals(flightNumber, that.flightNumber) && Objects.equals(cruiseAltitude, that.cruiseAltitude) && Objects.equals(currentAltitude, that.currentAltitude) && Objects.equals(scheduled, that.scheduled) && phase == that.phase && Objects.equals(direction, that.direction) && Objects.equals(speedHorizontal, that.speedHorizontal) && Objects.equals(speedVertical, that.speedVertical) && Objects.equals(waypoints, that.waypoints) && Objects.equals(currentPosition, that.currentPosition) && Objects.equals(trail, that.trail) && Objects.equals(progress, that.progress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightPlanId, flightNumber, cruiseAltitude, currentAltitude, scheduled, phase, direction, speedHorizontal, speedVertical, waypoints, currentPosition, trail, nextWaypointIndex, progress);
    }
}

package com.flightradarmsn.flightradar.simulation.service.utils;

import com.flightradarmsn.flightradar.model.dto.CoordinatesDTO;
import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import com.flightradarmsn.flightradar.simulation.state.FlightState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
* Classe utilitaria
* Possui metodos referentes a calculos matematicos e logicas
* */

@Component
public class FlightSimulationServiceUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Atualiza a posicao do aviao
    public void advanceOnRoute(FlightState state, double step) {
        // 1. Obtém os dados mais recentes diretamente do estado do voo.
        CoordinatesDTO currentPosition = state.getCurrentPosition();
        CoordinatesDTO targetPosition = state.getWaypoints().get(state.getNextWaypointIndex());
        double distance = calculateDistance(currentPosition, targetPosition);

        // Guarda de seguranca para evitar divisao por zero.
        if (distance < 0.000001) {
            return;
        }

        // 2. A logica de interpolacao permanece a mesma.
        double dirX = (targetPosition.getLongitude() - currentPosition.getLongitude()) / distance;
        double dirY = (targetPosition.getLatitude() - currentPosition.getLatitude()) / distance;

        double newLon = currentPosition.getLongitude() + dirX * step;
        double newLat = currentPosition.getLatitude() + dirY * step;

        if (state.getTrail().isEmpty() || !state.getTrail().getLast().equals(state.getCurrentPosition())) {
            state.getTrail().add(state.getCurrentPosition());
        }

        state.setCurrentPosition(new CoordinatesDTO(newLat, newLon, currentPosition.getDirection()));
    }

    // Atualiza o estado no momento em que um waypoint e alcancado
    public void processArrivalAtWaypoint(FlightState state, CoordinatesDTO targetPosition) {
        // Chegou ao waypoint
        state.setCurrentPosition(targetPosition);
        state.setNextWaypointIndex(state.getNextWaypointIndex() + 1);

        logger.info("Voo " + state.getFlightPlanId() + " alcançou o waypoint " + (state.getNextWaypointIndex() - 1));

        // NOVO: Atualiza a direcao para o proximo trecho da rota
        if (!state.hasFinished() && targetPosition.getDirection() != null) {
            state.getCurrentPosition().setDirection(targetPosition.getDirection());
            logger.info("Voo " + state.getFlightPlanId() + " atualizou a direção para: " + state.getCurrentPosition().getDirection());
        }

        if(state.hasFinished()) {
            logger.info("Voo " + state.getFlightPlanId() + " completou a rota.");
        }
    }

    // Faz o calculo da distancia entre um trail e outro
    public double calculateDistance(CoordinatesDTO currentPosition, CoordinatesDTO targetPosition) {
        double dx = currentPosition.getLongitude() - targetPosition.getLongitude();
        double dy = currentPosition.getLatitude() - targetPosition.getLatitude();
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Calcula a distancia ate o fim da rota
    public double calculateDistanceToEndOfRoute(FlightState state) {
        CoordinatesDTO finalWaypoint = state.getWaypoints().getLast();
        return calculateDistance(state.getCurrentPosition(), finalWaypoint);
    }

    // Calcula a porcentagem
    public void calculatePercentage(FlightState state) {
        double totalDistance = calculateDistance(state.getWaypoints().getFirst(), state.getWaypoints().getLast());
        double actualDistance = calculateDistance(state.getCurrentPosition(), state.getWaypoints().getLast());

        // Calculo da porcentagem restante
        double percentageRemaining = (actualDistance * 100 / totalDistance);

        // Inverte o valor para obter o progresso de 0 a 100
        state.setProgress(100 - (int) percentageRemaining);
    }

    /*
     * Calcula a distancia total da rota, do primeiro ao ultimo waypoint.
     */
    public double calculateTotalRouteDistance(FlightState state) {
        if (state.getWaypoints() == null || state.getWaypoints().size() < 2) {
            return 0.0;
        }
        return calculateDistance(state.getWaypoints().getFirst(), state.getWaypoints().getLast());
    }

    /*
     * Calcula a distância percorrida desde o inicio da rota.
     */
    public double calculateDistanceToOrigin(FlightState state) {
        if (state.getWaypoints() == null || state.getWaypoints().isEmpty()) {
            return 0.0;
        }
        return calculateDistance(state.getCurrentPosition(), state.getWaypoints().getFirst());
    }

    // Atualiza o flight plan de acordo com o state
    public void updateFlightPlan(FlightState state, FlightPlan plan) {
        plan.setFlightPhase(state.getPhase());
        plan.getLive().setAltitude(state.getCurrentAltitude());
        plan.getLive().setDirection(state.getDirection());
        plan.getLive().setSpeedHorizontal(state.getSpeedHorizontal());
        plan.getLive().setSpeedVertical(state.getSpeedVertical());
        plan.getLive().setLatitude(state.getCurrentPosition().getLatitude());
        plan.getLive().setLongitude(state.getCurrentPosition().getLongitude());
        plan.getLive().setProgress(state.getProgress());
        plan.getLive().setUpdated(LocalDateTime.now());
    }

}

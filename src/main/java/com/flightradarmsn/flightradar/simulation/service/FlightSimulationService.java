package com.flightradarmsn.flightradar.simulation.service;

import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.dto.CoordinatesDTO;
import com.flightradarmsn.flightradar.model.dto.FlightPlanDTO;
import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import com.flightradarmsn.flightradar.simulation.cache.FlightPlanMemoryDatabase;
import com.flightradarmsn.flightradar.simulation.cache.FlightStateMemoryDatabase;
import com.flightradarmsn.flightradar.simulation.state.FlightState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightSimulationService {

    @Autowired
    private FlightStateMemoryDatabase flightStateMemoryDatabase;

    @Autowired
    private FlightPlanMemoryDatabase flightPlanMemoryDatabase;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void start(FlightPlanDTO plan) {
        List<CoordinatesDTO> waypoints = plan.getRoute().getWaypoints();

        if(!flightStateMemoryDatabase.flightStateExists(plan.getId()) && plan.getRoute() != null) {

            // Defini o estado de voo
            FlightState newState = new FlightState(
                    plan.getId(),
                    plan.getFlight().getNumber(),
                    plan.getLive().getAltitude(),
                    plan.getLive().getDirection(),
                    plan.getLive().getSpeedHorizontal(),
                    plan.getLive().getSpeedVertical(),
                    plan.getRoute().getWaypoints()
            );

            // Define a direção inicial do voo, que é a direção do primeiro waypoint
            if (waypoints.getFirst().getDirection() != null) {
                newState.getCurrentPosition().setDirection(waypoints.getFirst().getDirection());
            }

            flightPlanMemoryDatabase.save(ObjectMapper.parseObject(plan, FlightPlan.class));
            flightStateMemoryDatabase.save(newState);
            logger.info("Simulação iniciada para FlightPlan ID:" + plan.getId());
        }
    }

    public void updateAllFlightsPositions() {
        for (FlightState state : flightStateMemoryDatabase.findAll()) {
            if(state.hasFinished()) continue;

            CoordinatesDTO currentPosition = state.getCurrentPosition();
            CoordinatesDTO targetPosition = state.getWaypoints().get(state.getNextWaypointIndex());

            final double speed = (double) state.getSpeedHorizontal() / 10000;
            double distance = calculateDistance(currentPosition, targetPosition);

            if(distance < speed) {
                processArrivalAtWaypoint(state, targetPosition);
            } else {
                advanceOnRoute(state, targetPosition, currentPosition, distance, speed);
            }

            double totalDistance = calculateDistance(state.getWaypoints().getFirst(), state.getWaypoints().getLast());
            double actualDistance = calculateDistance(state.getCurrentPosition(), state.getWaypoints().getLast());

            // O cálculo da porcentagem restante
            double percentageRemaining = (actualDistance * 100 / totalDistance);

            // Inverte o valor para obter o progresso de 0 a 100
            state.setProgress(100 - (int) percentageRemaining);

            flightStateMemoryDatabase.update(state);
        }
        updateAllFlightPlans();
    }

    private void updateAllFlightPlans() {
        for(FlightState state : flightStateMemoryDatabase.findAll().stream().toList()) {
            FlightPlan plan = flightPlanMemoryDatabase.findFlightPlanById(state.getFlightPlanId());
            updateFlightPlan(state, plan);
            flightPlanMemoryDatabase.update(plan);
        }
    }

    // Atualiza a posição do avião
    private void advanceOnRoute(FlightState state, CoordinatesDTO targetPosition, CoordinatesDTO currentPosition, double distance, double speed) {
        // Ainda a caminho, interpola a posição
        double dirX = (targetPosition.getLongitude() - currentPosition.getLongitude()) / distance;
        double dirY = (targetPosition.getLatitude() - currentPosition.getLatitude()) / distance;

        double newLon = currentPosition.getLongitude() + dirX * speed;
        double newLat = currentPosition.getLatitude() + dirY * speed;
        if(!state.getTrail().getLast().equals(state.getCurrentPosition())) state.getTrail().add(state.getCurrentPosition());

        state.setCurrentPosition(new CoordinatesDTO(newLat, newLon, currentPosition.getDirection()));
    }

    // Atualiza o estado no momento em que um waypoint é alcançado
    private void processArrivalAtWaypoint(FlightState state, CoordinatesDTO targetPosition) {
        // Chegou ao waypoint
        state.setCurrentPosition(targetPosition);
        state.setNextWaypointIndex(state.getNextWaypointIndex() + 1);

        logger.info("Voo " + state.getFlightPlanId() + " alcançou o waypoint " + (state.getNextWaypointIndex() - 1));

        // NOVO: Atualiza a direção para o próximo trecho da rota
        if (!state.hasFinished() && targetPosition.getDirection() != null) {
            state.getCurrentPosition().setDirection(targetPosition.getDirection());
            logger.info("Voo " + state.getFlightPlanId() + " atualizou a direção para: " + state.getCurrentPosition().getDirection());
        }

        if(state.hasFinished()) {
            logger.info("Voo " + state.getFlightPlanId() + " completou a rota.");
        }
    }

    // Faz o cálculo da distância entre um trail e outro
    private double calculateDistance(CoordinatesDTO currentPosition, CoordinatesDTO targetPosition) {
        double dx = currentPosition.getLongitude() - targetPosition.getLongitude();
        double dy = currentPosition.getLatitude() - targetPosition.getLatitude();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private void updateFlightPlan(FlightState state, FlightPlan plan) {
        plan.getLive().setAltitude(state.getAltitude());
        plan.getLive().setDirection(state.getDirection());
        plan.getLive().setSpeedHorizontal(state.getSpeedHorizontal());
        plan.getLive().setSpeedVertical(state.getSpeedVertical());
        plan.getLive().setLatitude(state.getCurrentPosition().getLatitude());
        plan.getLive().setLongitude(state.getCurrentPosition().getLongitude());
        plan.getLive().setUpdated(LocalDateTime.now());
    }

}

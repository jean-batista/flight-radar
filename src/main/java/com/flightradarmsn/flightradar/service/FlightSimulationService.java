package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.model.dto.FlightPlanDTO;
import com.flightradarmsn.flightradar.model.dto.TrailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Classe responsável por armazenar o estado dos voos
@Service
public class FlightSimulationService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Armazena os planos de voos em memória
    private final Map<Long, FlightPlanDTO> activeSimulations = new ConcurrentHashMap<>();

    // Inicia a simulação
    public void startOrUpdateSimulation(FlightPlanDTO plan) {
        // putIfAbsent garante que uma nova simulação só será criada se ela não existir
        if(plan.getId() == null) throw new RuntimeException("ID é nulo");
        activeSimulations.putIfAbsent(plan.getId(), plan);
        logger.info("Simulação iniciada para o ID: " + plan.getId());
    }

    public List<FlightPlanDTO> getFlightPlans() {
        return activeSimulations.values().stream().toList();
    }

    public FlightPlanDTO getFlightPlan(Long id) {
        return activeSimulations.get(id);
    }

    // Atualiza a posição de todos os planos de voos ativos
    public void updateAllFlightPositions() {

        logger.debug("Updated positions");

        // Realiza operações para cada plano de voo contido em activeSimulations
        for(FlightPlanDTO plan : activeSimulations.values()) {

            // Calcula a velocidade do avião
            final double speed = (double) plan.getLive().getSpeedHorizontal() / 10000;
            logger.debug(String.valueOf(speed));

            // Caso o percurso tenha terminado
            if(plan.getLive().getPredictedTrail().isEmpty()) {
                plan.setFlightStatus("landed");
                plan.getLive().setGround(true);
                continue;
            }

            // Defini a posição atual
            TrailDTO currentPosition = new TrailDTO();
            currentPosition.setLatitude(plan.getLive().getLatitude());
            currentPosition.setLongitude(plan.getLive().getLongitude());

            TrailDTO targetPosition = plan.getLive().getPredictedTrail().getFirst();

            // Calcula a distância entre um trail e o outro
            double distance = calculateDistance(currentPosition, targetPosition);

            if(distance < speed) {
                /* Quando um determinado ponto é alcançado */
                // Remove um ponto do predicted_trail
                TrailDTO reachedWaypoint = plan.getLive().getPredictedTrail().removeFirst();
                // Adiciona o ponto no trail
                plan.getLive().getTrail().add(reachedWaypoint);

                // Adiciona o ponto alcançado nas coordenadas atuais
                plan.getLive().setLatitude(reachedWaypoint.getLatitude());
                plan.getLive().setLongitude(reachedWaypoint.getLongitude());

                logger.info("Voo com ID " + plan.getId() + " chegou ao waypoint: " + reachedWaypoint);
            } else {
                // Calculas as direções no eixo X e Y
                double dirX = (targetPosition.getLongitude() - currentPosition.getLongitude()) / distance;
                double dirY = (targetPosition.getLatitude() - currentPosition.getLatitude()) / distance;

                // Calcula a nova longitude e latitude
                double newLongitude = currentPosition.getLongitude() + dirX * speed;
                double newLatitude = currentPosition.getLatitude() + dirY * speed;

                // Defini a latitude e longitude atual como a nova latitude e longitude calculada
                plan.getLive().setLatitude(newLatitude);
                plan.getLive().setLongitude(newLongitude);
            }
            plan.getLive().setUpdated(LocalDateTime.now());
        }
    }

    // Faz o cálculo da distância entre um trail e outro
    private double calculateDistance(TrailDTO currentPosition, TrailDTO targetPosition) {
        double dx = currentPosition.getLongitude() - targetPosition.getLongitude();
        double dy = currentPosition.getLatitude() - targetPosition.getLatitude();
        return Math.sqrt(dx * dx + dy * dy);
    }

}

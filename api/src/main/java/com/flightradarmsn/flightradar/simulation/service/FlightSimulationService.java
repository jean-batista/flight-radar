package com.flightradarmsn.flightradar.simulation.service;

import com.flightradarmsn.flightradar.mapper.ObjectMapper;
import com.flightradarmsn.flightradar.model.entities.Coordinates;
import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import com.flightradarmsn.flightradar.model.enums.FlightPhase;
import com.flightradarmsn.flightradar.repository.FlightPlanRepository;
import com.flightradarmsn.flightradar.simulation.cache.FlightPlanMemoryDatabase;
import com.flightradarmsn.flightradar.simulation.cache.FlightStateMemoryDatabase;
import com.flightradarmsn.flightradar.simulation.exceptions.SimulationResourceNotFoundException;
import com.flightradarmsn.flightradar.simulation.mapper.SimulationMapper;
import com.flightradarmsn.flightradar.simulation.service.utils.FlightSimulationServiceUtils;
import com.flightradarmsn.flightradar.simulation.state.FlightState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.flightradarmsn.flightradar.simulation.service.utils.SimulationConstants.*;

/*
* Classe responsavel pelos servicos de simulacao
* Inicia uma simulacao
* Atualiza os estados
* Atualiza os planos de voos de acordo com as informacoes do estado
* */

@Service
public class FlightSimulationService {

    @Autowired
    private FlightPlanRepository flightPlanRepository;

    @Autowired
    private FlightStateMemoryDatabase flightStateMemoryDatabase;

    @Autowired
    private FlightPlanMemoryDatabase flightPlanMemoryDatabase;

    @Autowired
    private FlightSimulationServiceUtils utils;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Metodo que inicia a simulacao
    public void start(FlightPlan plan) {
        if(plan == null) throw new IllegalArgumentException("O plano de voo não pode ser nulo");

        List<Coordinates> waypoints = plan.getRoute().getWaypoints();

        if(!flightStateMemoryDatabase.flightStateExists(plan.getId()) && plan.getRoute() != null) {

            // Define o estado de voo
            FlightState newState = SimulationMapper.flightPlanToFlightState(plan);

            // Define a direcao inicial do voo, que e a direcao do primeiro waypoint
            if (waypoints.getFirst().getDirection() != null) {
                newState.getCurrentPosition().setDirection(waypoints.getFirst().getDirection());
            }

            flightPlanMemoryDatabase.save(ObjectMapper.parseObject(plan, FlightPlan.class));
            flightStateMemoryDatabase.save(newState);
            logger.info("Simulação iniciada para FlightPlan ID:" + plan.getId());
        }
    }

    // Metodo que atualiza as posicoes
    public void updateAllFlightsPositions() {
        for (FlightState state : flightStateMemoryDatabase.findAll()) {

            // Verifica se o voo ja terminou
            if(state.hasFinished()) continue;

            /*
            * Verifica se o voo esta marcado como "SHEDULED"
            * Verifica se o horario de decolagem e antes do horario atual (caso "true", o aviao deve decolar)
            * */
            if (state.getPhase() == FlightPhase.SCHEDULED && state.getScheduled().isBefore(OffsetDateTime.now(ZoneOffset.UTC))) {
                // Define o fase de voo como "TAKING_OFF (decolando)"
                state.setPhase(FlightPhase.TAKING_OFF);
                logger.info("🛫 Flight {} taking off (scheduled {}, now {})",
                        state.getFlightNumber(), state.getScheduled(), OffsetDateTime.now(ZoneOffset.UTC));
            }

            /*
            * --- MOVIMENTO HORIZONTAL ---
            * Verifica se o aviao ja pousou
            * Verifica se o aviao esta marcado como "SCHEDULED"
            * */
            if (state.getPhase() == FlightPhase.LANDED || state.getPhase() == FlightPhase.SCHEDULED) {
                continue;
            }

            /*
            * --- MOVIMENTO HORIZONTAL SIMPLIFICADO ---
            * Verifica se o voo nao terminou
            * */
            if (!state.hasFinished()) {
                // Obtemos o alvo atual para calcular a distancia
                Coordinates targetPosition = state.getWaypoints().get(state.getNextWaypointIndex());
                double distance = utils.calculateDistance(state.getCurrentPosition(), targetPosition);

                // A chamada para os metodos utilitarios agora e mais simples e segura
                if(distance < HORIZONTAL_STEP) {
                    utils.processArrivalAtWaypoint(state, targetPosition);
                } else {
                    // Passamos apenas o 'state' e o 'step'. O metodo faz o resto.
                    utils.advanceOnRoute(state, HORIZONTAL_STEP);
                }
            }

            // --- MOVIMENTO VERTICAL ---

            // Calcula a distancia ate o destino
            double distanceToDestination = utils.calculateDistanceToEndOfRoute(state);

            /*
            * Executa uma acao de acordo com a fase do voo
            * */
            switch (state.getPhase()) {
                // Fase de decolagem
                case TAKING_OFF:
                    state.setSpeedVertical(CLIMB_RATE_FT_PER_SEC);
                    state.setCurrentAltitude(state.getCurrentAltitude() + state.getSpeedVertical());
                    // Transição -> Nivelar em 10.000 pés
                    if (state.getCurrentAltitude() >= INITIAL_CLIMB_ALTITUDE_FT) {
                        state.setCurrentAltitude(INITIAL_CLIMB_ALTITUDE_FT);
                        state.setSpeedVertical(0);
                        state.setPhase(FlightPhase.INITIAL_CLIMB_LEVEL_OFF);
                        logger.info("Voo {}: Nivelando em 10.000 pés.", state.getFlightPlanId());
                    }
                    break;
                // Fase de subida ate os 10000 pes
                case INITIAL_CLIMB_LEVEL_OFF:
                    // Transicao -> Subir para cruzeiro depois de percorrer uma distancia inicial
                    if (utils.calculateDistanceToOrigin(state) >= INITIAL_LEVEL_OFF_DISTANCE) {
                        state.setPhase(FlightPhase.CLIMB_TO_CRUISE);
                        logger.info("Voo {}: Saindo de 10.000 pés, subindo para cruzeiro.", state.getFlightPlanId());
                    }
                    break;
                // Fase de subida ate a altitude de cruzeiro
                case CLIMB_TO_CRUISE:
                    state.setSpeedVertical(CLIMB_RATE_FT_PER_SEC);
                    state.setCurrentAltitude(state.getCurrentAltitude() + state.getSpeedVertical());
                    // Transicao -> Cruzeiro
                    if (state.getCurrentAltitude() >= state.getCruiseAltitude()) {
                        state.setCurrentAltitude(state.getCruiseAltitude());
                        state.setSpeedVertical(0);
                        state.setPhase(FlightPhase.CRUISING);
                        logger.info("Voo {}: Atingiu altitude de cruzeiro.", state.getFlightPlanId());
                    }
                    break;
                // Fase de cruzeiro
                case CRUISING:
                    // Logica de Topo de Descida (TOD)
                    double altitudeToLoseForDescent = state.getCruiseAltitude() - INITIAL_CLIMB_ALTITUDE_FT;
                    double secondsForDescent = altitudeToLoseForDescent / Math.abs(DESCENT_RATE_FT_PER_SEC);
                    double distanceForDescent = secondsForDescent * HORIZONTAL_STEP;

                    // A distancia para o TOD deve considerar a descida E o patamar de aproximação
                    double todDistance = distanceForDescent + APPROACH_LEVEL_OFF_DISTANCE;

                    if (distanceToDestination <= todDistance) {
                        state.setPhase(FlightPhase.DESCENDING_TO_APPROACH);
                        logger.info("Voo {}: Iniciando descida para 10.000 pés (TOD).", state.getFlightPlanId());
                    }
                    break;
                // Fase de descida da altitude de cruzeiro ate os 10000 pes
                case DESCENDING_TO_APPROACH:
                    state.setSpeedVertical(DESCENT_RATE_FT_PER_SEC);
                    state.setCurrentAltitude(Math.max(state.getCurrentAltitude() + state.getSpeedVertical(), INITIAL_CLIMB_ALTITUDE_FT));
                    // Transição -> Nivelar em 10.000 pés
                    if (state.getCurrentAltitude() <= INITIAL_CLIMB_ALTITUDE_FT) {
                        state.setCurrentAltitude(INITIAL_CLIMB_ALTITUDE_FT);
                        state.setSpeedVertical(0);
                        state.setPhase(FlightPhase.APPROACH_LEVEL_OFF);
                        logger.info("Voo {}: Nivelando em 10.000 pés para aproximação.", state.getFlightPlanId());
                    }
                    break;
                // Fase de aproimacao
                case APPROACH_LEVEL_OFF:
                    double altitudeToLoseForApproach = INITIAL_CLIMB_ALTITUDE_FT - 0;
                    double secondsForApproach = altitudeToLoseForApproach / Math.abs(APPROACH_RATE_FT_PER_SEC);
                    double distanceForApproach = secondsForApproach * HORIZONTAL_STEP;

                    // Transicao -> Aproximação final
                    if (distanceToDestination <= distanceForApproach) {
                        state.setPhase(FlightPhase.FINAL_APPROACH);
                        logger.info("Voo {}: Iniciando aproximação final.", state.getFlightPlanId());
                    }
                    break;
                // Fase de aproximacao final (Landing)
                case FINAL_APPROACH:
                    state.setSpeedVertical(APPROACH_RATE_FT_PER_SEC);
                    state.setCurrentAltitude(Math.max(state.getCurrentAltitude() + state.getSpeedVertical(), 0));
                    // Transicao -> Pousado
                    if (state.hasFinished() || state.getCurrentAltitude() <= 10) {
                        state.setCurrentAltitude(0);
                        state.setSpeedVertical(0);
                        state.setSpeedHorizontal(0);
                        state.setPhase(FlightPhase.LANDED);
                        if (!state.getWaypoints().isEmpty()) {
                            state.setCurrentPosition(state.getWaypoints().getLast());
                        }
                        logger.info("Voo {} POUSOU.", state.getFlightPlanId());
                    }
                    break;
            }

            // Atualiza o progresso e os objetos em memoria
            utils.calculatePercentage(state);
            flightStateMemoryDatabase.update(state);

            // Obtem o plano de voo referente ao estado
            FlightPlan planToUpdate = flightPlanMemoryDatabase.findFlightPlanById(state.getFlightPlanId()).orElseThrow(
                    () -> new SimulationResourceNotFoundException("Não foi possível encontrar o plano de voo com o id: " + state.getFlightPlanId())
            );

            /*
            * Caso o plano de voo for encontrado
            * Sera atualizado de acordo com as informacoes no state
            * */
            if (planToUpdate != null) {
                utils.updateFlightPlan(state, planToUpdate);
                flightPlanMemoryDatabase.update(planToUpdate);
            }
        }
    }

    public void updateDatabase() {
        List<FlightPlan> list = flightPlanMemoryDatabase.findAll();
        flightPlanRepository.saveAll(list);
    }

}

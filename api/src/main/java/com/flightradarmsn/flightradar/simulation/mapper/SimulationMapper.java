package com.flightradarmsn.flightradar.simulation.mapper;

import com.flightradarmsn.flightradar.model.dto.FlightPlanDTO;
import com.flightradarmsn.flightradar.simulation.state.FlightState;

import java.time.LocalDateTime;

/*
* Classe responsavel por mapear objetos
* FlightPlan <=> FlightState
* */

public class SimulationMapper {

    /*
    * Cria um estado de voo de acordo com as
    * informcaoes de um plano de voo
    * */
    public static FlightState flightPlanToFlightState(FlightPlanDTO plan) {
        return new FlightState(
                plan.getId(),
                plan.getFlight().getNumber(),
                plan.getFlight().getCruiseAltitude(),
                plan.getLive().getAltitude(),
                plan.getDeparture().getScheduled(),
                plan.getFlightPhase(),
                plan.getLive().getDirection(),
                plan.getLive().getSpeedHorizontal(),
                plan.getLive().getSpeedVertical(),
                plan.getRoute().getWaypoints()
        );
    }

    /*
    * Cria um plano de voo de acordo com as
    * informacoes de um estado
    * */
    public static FlightPlanDTO flightStateToFlightPlan(FlightState state) {
        FlightPlanDTO plan = new FlightPlanDTO();
        plan.getLive().setAltitude(state.getCurrentAltitude());
        plan.getLive().setDirection(state.getDirection());
        plan.getLive().setSpeedHorizontal(state.getSpeedHorizontal());
        plan.getLive().setSpeedVertical(state.getSpeedVertical());
        plan.getLive().setLatitude(state.getCurrentPosition().getLatitude());
        plan.getLive().setLongitude(state.getCurrentPosition().getLongitude());
        plan.getLive().setUpdated(LocalDateTime.now());
        return plan;
    }

}

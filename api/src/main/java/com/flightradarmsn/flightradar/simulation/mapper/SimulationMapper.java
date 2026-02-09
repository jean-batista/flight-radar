package com.flightradarmsn.flightradar.simulation.mapper;

import com.flightradarmsn.flightradar.model.dto.response.FlightPlanResponseDTO;
import com.flightradarmsn.flightradar.model.entities.FlightPlan;
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
    public static FlightState flightPlanToFlightState(FlightPlan plan) {
        if(plan == null) throw new IllegalArgumentException("O plano de voo não pode ser nulo");
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
    public static FlightPlanResponseDTO flightStateToFlightPlan(FlightState state) {
        if(state == null) throw new IllegalArgumentException("O State não pode ser nulo");
        FlightPlanResponseDTO plan = new FlightPlanResponseDTO();
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

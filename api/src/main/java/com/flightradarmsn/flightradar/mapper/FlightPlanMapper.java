package com.flightradarmsn.flightradar.mapper;

import com.flightradarmsn.flightradar.exceptions.ResourceNotFoundException;
import com.flightradarmsn.flightradar.model.dto.request.*;
import com.flightradarmsn.flightradar.model.entities.*;
import com.flightradarmsn.flightradar.repository.AircraftRepository;
import com.flightradarmsn.flightradar.repository.AirlineRepository;
import com.flightradarmsn.flightradar.repository.AirportRepository;
import com.flightradarmsn.flightradar.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightPlanMapper {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private RouteRepository routeRepository;

    public FlightPlan flightPlanDTOToFlightPlanEntity(FlightPlanDTO flightPlanDTO) {

        FlightPlan flightPlan = new FlightPlan();
        Airline airline = airlineRepository.findById(flightPlanDTO.getAirlineId()).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma airline com o id " + flightPlanDTO.getAirlineId())
        );
        Aircraft aircraft = aircraftRepository.findById(flightPlanDTO.getAircraftId()).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma aeronave com o id " + flightPlanDTO.getAircraftId())
        );
        Route route = routeRepository.findById(flightPlanDTO.getRouteId()).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar uma rota com o id " + flightPlanDTO.getRouteId())
        );

        flightPlan.setFlightDate(flightPlanDTO.getFlightDate());
        flightPlan.setFlightPhase(flightPlanDTO.getFlightPhase());
        flightPlan.setDeparture(departure(flightPlanDTO.getDeparture()));
        flightPlan.setArrival(arrivalDTOToEntity(flightPlanDTO.getArrival()));
        flightPlan.setAirline(airline);
        flightPlan.setFlight(flightDTOToEntity(flightPlanDTO.getFlight()));
        flightPlan.setAircraft(aircraft);
        flightPlan.setRoute(route);
        flightPlan.setLive(liveDTOToEntity(flightPlanDTO.getLive()));

        return flightPlan;
    }

    private Departure departure(DepartureDTO departureDTO) {
        Departure departure = new Departure();
        Airport departureAirport = airportRepository.findById(departureDTO.getAirportId()).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar um aeroporto com o id " + departureDTO.getAirportId())
        );
        departure.setAirport(departureAirport);
        departure.setTimezone(departureDTO.getTimezone());
        departure.setIata(departureDTO.getIata());
        departure.setIcao(departureDTO.getIcao());
        departure.setTerminal(departureDTO.getTerminal());
        departure.setGate(departureDTO.getGate());
        departure.setScheduled(departureDTO.getScheduled());
        departure.setEstimated(departureDTO.getEstimated());
        departure.setActual(departureDTO.getActual());
        departure.setEstimatedRunway(departureDTO.getEstimatedRunway());
        departure.setActualRunway(departureDTO.getActualRunway());
        return departure;
    }

    private Arrival arrivalDTOToEntity(ArrivalDTO arrivalDTO) {
        Arrival arrival = new Arrival();
        Airport departureAirport = airportRepository.findById(arrivalDTO.getAirportId()).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar um aeroporto com o id " + arrivalDTO.getAirportId())
        );
        arrival.setAirport(departureAirport);
        arrival.setTimezone(arrivalDTO.getTimezone());
        arrival.setIata(arrivalDTO.getIata());
        arrival.setIcao(arrivalDTO.getIcao());
        arrival.setTerminal(arrivalDTO.getTerminal());
        arrival.setGate(arrivalDTO.getGate());
        arrival.setBaggage(arrivalDTO.getBaggage());
        arrival.setScheduled(arrivalDTO.getScheduled());
        arrival.setEstimated(arrivalDTO.getEstimated());
        arrival.setActual(arrivalDTO.getActual());
        arrival.setEstimatedRunway(arrivalDTO.getEstimatedRunway());
        arrival.setActualRunway(arrivalDTO.getActualRunway());
        return arrival;
    }

    private Flight flightDTOToEntity(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setNumber(flightDTO.getNumber());
        flight.setIata(flightDTO.getIata());
        flight.setIcao(flightDTO.getIcao());
        flight.setCodeshared(flightDTO.getCodeshared());
        flight.setCruiseAltitude(flightDTO.getCruiseAltitude());
        return flight;
    }

    private Live liveDTOToEntity(LiveDTO liveDTO) {
        Live live = new Live();
        live.setUpdated(liveDTO.getUpdated());
        live.setLatitude(liveDTO.getLatitude());
        live.setLongitude(liveDTO.getLongitude());
        live.setAltitude(liveDTO.getAltitude());
        live.setDirection(liveDTO.getDirection());
        live.setSpeedHorizontal(liveDTO.getSpeedHorizontal());
        live.setSpeedVertical(liveDTO.getSpeedVertical());
        live.setProgress(liveDTO.getProgress());
        live.setGround(liveDTO.getGround());
        return live;
    }

}

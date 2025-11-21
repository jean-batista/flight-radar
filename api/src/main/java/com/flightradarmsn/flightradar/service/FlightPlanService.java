package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.model.dto.FlightPlanDTO;
import com.flightradarmsn.flightradar.model.dto.FlightPlanMinDTO;
import com.flightradarmsn.flightradar.model.dto.SearchFlightDTO;
import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import com.flightradarmsn.flightradar.simulation.cache.FlightPlanMemoryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseListObjects;
import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseObject;

@Service
public class FlightPlanService {

    @Autowired
    private FlightPlanMemoryDatabase database;

    public FlightPlanDTO save(FlightPlanDTO flightPlanDTO) {
        FlightPlan entity = parseObject(flightPlanDTO, FlightPlan.class);
        return parseObject(database.save(entity), FlightPlanDTO.class);
    }

    public FlightPlanDTO findById(Long id) {
        var entity = database.findFlightPlanById(id);
        return parseObject(entity, FlightPlanDTO.class);
    }

    public List<FlightPlanDTO> findAll() {
        return parseListObjects(database.findAll(), FlightPlanDTO.class);
    }

    public List<FlightPlanMinDTO> findAllMin() {
        return parseListObjects(database.findAll(), FlightPlanMinDTO.class);
    }

    public List<FlightPlanDTO> searchFlights(SearchFlightDTO criteria) {
        // 1. Se tiver ID, busca direta
        if (criteria.getId() != null) {
            FlightPlanDTO flight = findById(criteria.getId());
            return flight != null ? List.of(flight) : List.of();
        }

        List<FlightPlanDTO> list = findAll();

        return list.stream()
                // Filtro de Origem (Aeroporto OU Cidade)
                .filter(plan -> {
                    if (criteria.getOrigin() == null || criteria.getOrigin().isBlank()) return true; // Pula se vazio
                    String search = criteria.getOrigin().toLowerCase();
                    String airportName = plan.getDeparture().getAirport().getAirportName().toLowerCase();
                    String cityName = plan.getDeparture().getAirport().getCityName().toLowerCase();
                    return airportName.contains(search) || cityName.contains(search);
                })
                // Filtro de Destino (Aeroporto OU Cidade)
                .filter(plan -> {
                    if (criteria.getDestiny() == null || criteria.getDestiny().isBlank()) return true;
                    String search = criteria.getDestiny().toLowerCase();
                    String airportName = plan.getArrival().getAirport().getAirportName().toLowerCase();
                    String cityName = plan.getArrival().getAirport().getCityName().toLowerCase();
                    return airportName.contains(search) || cityName.contains(search);
                })
                // Filtro de Companhia Aérea
                .filter(plan -> {
                    if (criteria.getAirline() == null || criteria.getAirline().isBlank()) return true;
                    String search = criteria.getAirline().toLowerCase();
                    String airlineName = plan.getAirline().getName().toLowerCase();
                    return airlineName.contains(search);
                })
                .toList();
    }

}

package com.flightradarmsn.flightradar.service;

import com.flightradarmsn.flightradar.exceptions.ResourceNotFoundException;
import com.flightradarmsn.flightradar.mapper.FlightPlanMapper;
import com.flightradarmsn.flightradar.model.dto.request.FlightPlanDTO;
import com.flightradarmsn.flightradar.model.dto.request.SearchFlightDTO;
import com.flightradarmsn.flightradar.model.dto.response.FlightPlanMinResponseDTO;
import com.flightradarmsn.flightradar.model.dto.response.FlightPlanResponseDTO;
import com.flightradarmsn.flightradar.model.entities.FlightPlan;
import com.flightradarmsn.flightradar.repository.FlightPlanRepository;
import com.flightradarmsn.flightradar.simulation.cache.FlightPlanMemoryDatabase;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseListObjects;
import static com.flightradarmsn.flightradar.mapper.ObjectMapper.parseObject;

@Service
public class FlightPlanService {

    @Autowired
    private FlightPlanRepository repository;

    @Autowired
    private FlightPlanMemoryDatabase memoryDatabase;

    @Autowired
    private FlightPlanMapper flightPlanMapper;

    @Transactional
    public FlightPlanResponseDTO save(FlightPlanDTO flightPlanDTO) {
        if(flightPlanDTO == null) throw new IllegalArgumentException("Não é possivel salvar um plano de voo nulo");
        FlightPlan entity = flightPlanMapper.flightPlanDTOToFlightPlanEntity(flightPlanDTO);
        return parseObject(repository.save(entity), FlightPlanResponseDTO.class);
    }

    public FlightPlanResponseDTO findById(Long id) {
        FlightPlan entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar um plano de voo com o id " + id)
        );
        return parseObject(entity, FlightPlanResponseDTO.class);
    }

    public List<FlightPlanResponseDTO> findAll() {
        return parseListObjects(repository.findAll(), FlightPlanResponseDTO.class);
    }

    public List<FlightPlanMinResponseDTO> findAllMin() {
        return parseListObjects(repository.findAll(), FlightPlanMinResponseDTO.class);
    }

    public void delete(Long id) {
        FlightPlan flightPlan = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar um plano de voo com o id " + id)
        );
        repository.delete(flightPlan);
    }

    public List<FlightPlanResponseDTO> searchFlights(SearchFlightDTO criteria) {
        // 1. Se tiver ID, busca direta
        if (criteria.getId() != null) {
            FlightPlanResponseDTO flight = findById(criteria.getId());
            return flight != null ? List.of(flight) : List.of();
        }

        List<FlightPlanResponseDTO> list = findAll();

        var filteredList =  list.stream()
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

        if(filteredList.isEmpty())
            throw new ResourceNotFoundException("Não foi possível encontrar voos com os critérios fornecidos");

        return filteredList;
    }

}

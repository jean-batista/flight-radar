package com.flightradarmsn.flightradar.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_route")
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String name;

    // Aeroporto de origem
    @ManyToOne
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private Airport origin;

    // Aeroporto de destino
    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destination;

    // Cria a tabela com os waypoints
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_route_waypoints", joinColumns = @JoinColumn(name = "route_id"))
    private List<Coordinates> waypoints;

    public Route() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public List<Coordinates> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Coordinates> waypoints) {
        this.waypoints = waypoints;
    }

    // Gera o nome da rota de acordo com o iata code
    @PrePersist
    @PreUpdate
    public void generateRouteName() {
        if(origin != null && destination != null) {
            this.name = origin.getIataCode() + "-" + destination.getIataCode();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(name, route.name) && Objects.equals(origin, route.origin) && Objects.equals(destination, route.destination) && Objects.equals(waypoints, route.waypoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, origin, destination, waypoints);
    }
}

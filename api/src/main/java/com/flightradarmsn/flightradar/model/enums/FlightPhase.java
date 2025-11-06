package com.flightradarmsn.flightradar.model.enums;

public enum FlightPhase {
    SCHEDULED(1),
    TAKING_OFF(2),              // Decolando até 10.000 pés
    INITIAL_CLIMB_LEVEL_OFF(3), // Nivelado a 10.000 pés
    CLIMB_TO_CRUISE(4),         // Subindo de 10.000 para altitude de cruzeiro
    CRUISING(5),                // Altitude de cruzeiro
    DESCENDING_TO_APPROACH(6),  // Descendo de cruzeiro para 10.000 pés
    APPROACH_LEVEL_OFF(7),      // Nivelado a 10.000 pés
    FINAL_APPROACH(8),          // Descendo de 10.000 pés para o pouso
    LANDED(9);                  // Voo finalizado

    private final int number;

    FlightPhase(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public static FlightPhase getFlightPhaseByNumber(int number) {
        for(FlightPhase phase : FlightPhase.values()) {
            if(phase.number == number) return phase;
        }
        return null;
    }

}

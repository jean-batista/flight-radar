package com.flightradarmsn.flightradar.simulation.service.utils;

/*
* Interface responsavel pela definicoes de constantes
* que sao utilizadas nos calculos da simulacao
* */

public interface SimulationConstants {

    /*
    * Velocidade de simulacao
    *
    * Aumentar esse valor faz com que a simulacao ocorra mais rapidamente
    * Diminuir esse valor faz com que a simulacao ocorra mais lentamente
    *
    * Alterar esse valor pode trazer erros aos calculos de subida e descida do aviao
    * */
    double HORIZONTAL_STEP = 0.025;

    // --- PERFIL VERTICAL (PES E PES POR SEGUNDO) ---
    int CLIMB_RATE_FT_PER_SEC = 500;
    int DESCENT_RATE_FT_PER_SEC = -500;
    int APPROACH_RATE_FT_PER_SEC = -175;

    // --- ALTITUDES DO PERFIL DE VOO (PES) ---
    int INITIAL_CLIMB_ALTITUDE_FT = 10000; // Patamar de subida e descida

    // --- DISTANCIAS PARA PATAMARES (EM GRAUS DE LAT/LON) ---
    // Define por quantos "graus" o aviao ficara nivelado apos a decolagem e antes do pouso
    double INITIAL_LEVEL_OFF_DISTANCE = HORIZONTAL_STEP * 30;
    double APPROACH_LEVEL_OFF_DISTANCE = HORIZONTAL_STEP * 30;
}
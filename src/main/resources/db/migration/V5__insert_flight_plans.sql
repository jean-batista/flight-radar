-- Inserindo 12 planos de voo com status variados (scheduled, active, landed)
-- Corrigido para corresponder à contagem exata de colunas da tabela tb_flight_plan.

-- Voo 1: Doméstico Brasil (Ponte Aérea) - POUSADO
INSERT INTO tb_flight_plan (
    flight_date, flight_status,
    departure_timezone, departure_iata, departure_icao, departure_terminal, departure_gate, departure_scheduled, departure_estimated, departure_actual, departure_estimated_runway, departure_actual_runway,
    arrival_timezone, arrival_iata, arrival_icao, arrival_terminal, arrival_gate, arrival_baggage, arrival_scheduled, arrival_estimated, arrival_actual, arrival_estimated_runway, arrival_actual_runway,
    flight_number, flight_iata, flight_icao, flight_codeshared,
    live_updated, live_latitude, live_longitude, live_altitude, live_direction, live_speed_horizontal, live_speed_vertical, live_is_ground,
    airline_id, aircraft_id, departure_airport_id, arrival_airport_id
) VALUES (
    '2025-09-14', 'landed',
    'America/Sao_Paulo', 'CGH', NULL, '1', '12', '2025-09-14 10:00:00', '2025-09-14 10:05:00', '2025-09-14 10:04:00', NULL, NULL,
    'America/Sao_Paulo', 'SDU', NULL, '1', '22', '3', '2025-09-14 11:00:00', '2025-09-14 11:02:00', '2025-09-14 10:59:00', NULL, NULL,
    '1201', 'G31201', 'GLO1201', false,
    '2025-09-14 11:00:00', -22.9105, -43.1631, 0, 0, 0, 0, true,
    10, 2, 49, 52
);

-- Voo 2: Doméstico Brasil (Nordeste) - ATIVO
INSERT INTO tb_flight_plan (
    flight_date, flight_status,
    departure_timezone, departure_iata, departure_icao, departure_terminal, departure_gate, departure_scheduled, departure_estimated, departure_actual, departure_estimated_runway, departure_actual_runway,
    arrival_timezone, arrival_iata, arrival_icao, arrival_terminal, arrival_gate, arrival_baggage, arrival_scheduled, arrival_estimated, arrival_actual, arrival_estimated_runway, arrival_actual_runway,
    flight_number, flight_iata, flight_icao, flight_codeshared,
    live_updated, live_latitude, live_longitude, live_altitude, live_direction, live_speed_horizontal, live_speed_vertical, live_is_ground,
    airline_id, aircraft_id, departure_airport_id, arrival_airport_id
) VALUES (
    '2025-09-14', 'active',
    'America/Sao_Paulo', 'GRU', 'SBGR', '2', '208', '2025-09-14 19:00:00', '2025-09-14 19:00:00', '2025-09-14 19:02:00', NULL, NULL,
    'America/Recife', 'REC', 'SBRF', '1', 'B12', '5', '2025-09-14 22:05:00', '2025-09-14 22:05:00', NULL, NULL, NULL,
    '4010', 'AD4010', 'AZU4010', false,
    '2025-09-14 21:17:00', -15.8692, -47.9172, 11277.6, 45, 880, 0, false,
    11, 1, 48, 69
);
-- Captura o ID do Voo 2 para usar nas tabelas de rota
SET @voo2_id = LAST_INSERT_ID();
-- Rota para Voo 2
INSERT INTO tb_trail_points (flight_plan_id, latitude, longitude) VALUES
(@voo2_id, -23.4356, -46.4731),
(@voo2_id, -19.6244, -43.9719);
INSERT INTO tb_predicted_trail_points (flight_plan_id, latitude, longitude) VALUES
(@voo2_id, -15.8692, -47.9172),
(@voo2_id, -12.9111, -38.3311),
(@voo2_id, -8.1264, -34.9231);


-- Voo 4: Longa Distância (Europa) - ATIVO
INSERT INTO tb_flight_plan (
    flight_date, flight_status,
    departure_timezone, departure_iata, departure_icao, departure_terminal, departure_gate, departure_scheduled, departure_estimated, departure_actual, departure_estimated_runway, departure_actual_runway,
    arrival_timezone, arrival_iata, arrival_icao, arrival_terminal, arrival_gate, arrival_baggage, arrival_scheduled, arrival_estimated, arrival_actual, arrival_estimated_runway, arrival_actual_runway,
    flight_number, flight_iata, flight_icao, flight_codeshared,
    live_updated, live_latitude, live_longitude, live_altitude, live_direction, live_speed_horizontal, live_speed_vertical, live_is_ground,
    airline_id, aircraft_id, departure_airport_id, arrival_airport_id
) VALUES (
    '2025-09-14', 'active',
    'America/Sao_Paulo', 'GRU', 'SBGR', '3', '302', '2025-09-14 16:45:00', '2025-09-14 16:50:00', '2025-09-14 16:55:00', NULL, NULL,
    'Europe/Lisbon', 'LIS', 'LPPT', '1', '42', '8', '2025-09-15 06:30:00', '2025-09-15 06:35:00', NULL, NULL, NULL,
    '82', 'TP82', 'TAP82', false,
    '2025-09-14 21:17:00', -2.5, -32.5, 11887, 35, 910, 0, false,
    23, 10, 48, 25
);
-- Captura o ID do Voo 4 para usar nas tabelas de rota
SET @voo4_id = LAST_INSERT_ID();
-- Rota para Voo 4
INSERT INTO tb_trail_points (flight_plan_id, latitude, longitude) VALUES
(@voo4_id, -23.4356, -46.4731),
(@voo4_id, -5.7676, -35.3653);
INSERT INTO tb_predicted_trail_points (flight_plan_id, latitude, longitude) VALUES
(@voo4_id, -2.5, -32.5),
(@voo4_id, 16.0, -24.0),
(@voo4_id, 38.7742, -9.1342);-- Inserindo 12 planos de voo com status variados (scheduled, active, landed)
                             -- Corrigido para corresponder à contagem exata de colunas da tabela tb_flight_plan.

                             -- Voo 1: Doméstico Brasil (Ponte Aérea) - POUSADO
                             INSERT INTO tb_flight_plan (
                                 flight_date, flight_status,
                                 departure_timezone, departure_iata, departure_icao, departure_terminal, departure_gate, departure_scheduled, departure_estimated, departure_actual, departure_estimated_runway, departure_actual_runway,
                                 arrival_timezone, arrival_iata, arrival_icao, arrival_terminal, arrival_gate, arrival_baggage, arrival_scheduled, arrival_estimated, arrival_actual, arrival_estimated_runway, arrival_actual_runway,
                                 flight_number, flight_iata, flight_icao, flight_codeshared,
                                 live_updated, live_latitude, live_longitude, live_altitude, live_direction, live_speed_horizontal, live_speed_vertical, live_is_ground,
                                 airline_id, aircraft_id, departure_airport_id, arrival_airport_id
                             ) VALUES (
                                 '2025-09-14', 'landed',
                                 'America/Sao_Paulo', 'CGH', NULL, '1', '12', '2025-09-14 10:00:00', '2025-09-14 10:05:00', '2025-09-14 10:04:00', NULL, NULL,
                                 'America/Sao_Paulo', 'SDU', NULL, '1', '22', '3', '2025-09-14 11:00:00', '2025-09-14 11:02:00', '2025-09-14 10:59:00', NULL, NULL,
                                 '1201', 'G31201', 'GLO1201', false,
                                 '2025-09-14 11:00:00', -22.9105, -43.1631, 0, 0, 0, 0, true,
                                 10, 2, 49, 52
                             );

                             -- Voo 2: Doméstico Brasil (Nordeste) - ATIVO
                             INSERT INTO tb_flight_plan (
                                 flight_date, flight_status,
                                 departure_timezone, departure_iata, departure_icao, departure_terminal, departure_gate, departure_scheduled, departure_estimated, departure_actual, departure_estimated_runway, departure_actual_runway,
                                 arrival_timezone, arrival_iata, arrival_icao, arrival_terminal, arrival_gate, arrival_baggage, arrival_scheduled, arrival_estimated, arrival_actual, arrival_estimated_runway, arrival_actual_runway,
                                 flight_number, flight_iata, flight_icao, flight_codeshared,
                                 live_updated, live_latitude, live_longitude, live_altitude, live_direction, live_speed_horizontal, live_speed_vertical, live_is_ground,
                                 airline_id, aircraft_id, departure_airport_id, arrival_airport_id
                             ) VALUES (
                                 '2025-09-14', 'active',
                                 'America/Sao_Paulo', 'GRU', 'SBGR', '2', '208', '2025-09-14 19:00:00', '2025-09-14 19:00:00', '2025-09-14 19:02:00', NULL, NULL,
                                 'America/Recife', 'REC', 'SBRF', '1', 'B12', '5', '2025-09-14 22:05:00', '2025-09-14 22:05:00', NULL, NULL, NULL,
                                 '4010', 'AD4010', 'AZU4010', false,
                                 '2025-09-14 21:17:00', -15.8692, -47.9172, 11277.6, 45, 880, 0, false,
                                 11, 1, 48, 69
                             );
                             -- Captura o ID do Voo 2 para usar nas tabelas de rota
                             SET @voo2_id = LAST_INSERT_ID();
                             -- Rota para Voo 2
                             INSERT INTO tb_trail_points (flight_plan_id, latitude, longitude) VALUES
                             (@voo2_id, -23.4356, -46.4731),
                             (@voo2_id, -19.6244, -43.9719);
                             INSERT INTO tb_predicted_trail_points (flight_plan_id, latitude, longitude) VALUES
                             (@voo2_id, -15.8692, -47.9172),
                             (@voo2_id, -12.9111, -38.3311),
                             (@voo2_id, -8.1264, -34.9231);


                             -- Voo 4: Longa Distância (Europa) - ATIVO
                             INSERT INTO tb_flight_plan (
                                 flight_date, flight_status,
                                 departure_timezone, departure_iata, departure_icao, departure_terminal, departure_gate, departure_scheduled, departure_estimated, departure_actual, departure_estimated_runway, departure_actual_runway,
                                 arrival_timezone, arrival_iata, arrival_icao, arrival_terminal, arrival_gate, arrival_baggage, arrival_scheduled, arrival_estimated, arrival_actual, arrival_estimated_runway, arrival_actual_runway,
                                 flight_number, flight_iata, flight_icao, flight_codeshared,
                                 live_updated, live_latitude, live_longitude, live_altitude, live_direction, live_speed_horizontal, live_speed_vertical, live_is_ground,
                                 airline_id, aircraft_id, departure_airport_id, arrival_airport_id
                             ) VALUES (
                                 '2025-09-14', 'active',
                                 'America/Sao_Paulo', 'GRU', 'SBGR', '3', '302', '2025-09-14 16:45:00', '2025-09-14 16:50:00', '2025-09-14 16:55:00', NULL, NULL,
                                 'Europe/Lisbon', 'LIS', 'LPPT', '1', '42', '8', '2025-09-15 06:30:00', '2025-09-15 06:35:00', NULL, NULL, NULL,
                                 '82', 'TP82', 'TAP82', false,
                                 '2025-09-14 21:17:00', -2.5, -32.5, 11887, 35, 910, 0, false,
                                 23, 10, 48, 25
                             );
                             -- Captura o ID do Voo 4 para usar nas tabelas de rota
                             SET @voo4_id = LAST_INSERT_ID();
                             -- Rota para Voo 4
                             INSERT INTO tb_trail_points (flight_plan_id, latitude, longitude) VALUES
                             (@voo4_id, -23.4356, -46.4731),
                             (@voo4_id, -5.7676, -35.3653);
                             INSERT INTO tb_predicted_trail_points (flight_plan_id, latitude, longitude) VALUES
                             (@voo4_id, -2.5, -32.5),
                             (@voo4_id, 16.0, -24.0),
                             (@voo4_id, 38.7742, -9.1342);
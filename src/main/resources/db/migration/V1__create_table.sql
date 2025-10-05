-- Tabela para armazenar as companhias aéreas (Airlines)
-- Esta é uma entidade independente com seu próprio ID.
CREATE TABLE IF NOT EXISTS tb_airline (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    iata VARCHAR(10),
    icao VARCHAR(10)
);

-- Tabela para armazenar as aeronaves (Aircrafts)
-- Esta também é uma entidade independente.
CREATE TABLE IF NOT EXISTS tb_aircraft (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    registration VARCHAR(255),
    iata VARCHAR(10),
    icao VARCHAR(10),
    icao24 VARCHAR(20)
);

-- Tabela para armazenar os aerportos (Airports)
-- Esta também é uma entidade independente
CREATE TABLE IF NOT EXISTS tb_airport (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    iata VARCHAR(10),
    icao VARCHAR(10),
    latitude DOUBLE,
    longitude DOUBLE,
    geoname_id BIGINT,
    timezone VARCHAR(50),
    gmt INT,
    phone_number VARCHAR(50),
    country_name VARCHAR(50),
    country_iso2 VARCHAR(10),
    city_iata_code VARCHAR(10)
);

-- Tabela de rotas
CREATE TABLE IF NOT EXISTS tb_route (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(10),
    origin_airport_id BIGINT,
    destination_airport_id BIGINT
);

-- Tabela de pontos da rota
CREATE TABLE IF NOT EXISTS tb_route_waypoints (
    route_id BIGINT NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE,
    direction DOUBLE,

    -- Chave Estrangeira ligando cada ponto ao seu respectivo FlightPlan
    FOREIGN KEY (route_id) REFERENCES tb_route(id)
);

-- Tabela principal para os planos de voo (FlightPlan)
-- Contém os campos próprios e os campos das classes embutidas (Embedded).
CREATE TABLE IF NOT EXISTS tb_flight_plan (
    -- Campos próprios da entidade FlightPlan
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_date DATE,
    flight_status VARCHAR(255),

    -- Campos embutidos (Embedded) de Departure com prefixo
    departure_timezone VARCHAR(255),
    departure_iata VARCHAR(10),
    departure_icao VARCHAR(10),
    departure_terminal VARCHAR(10),
    departure_gate VARCHAR(10),
    departure_delay INT,
    departure_scheduled VARCHAR(255),
    departure_estimated VARCHAR(255),
    departure_actual VARCHAR(255),
    departure_estimated_runway VARCHAR(255),
    departure_actual_runway VARCHAR(255),

    -- Campos embutidos (Embedded) de Arrival com prefixo
    arrival_timezone VARCHAR(255),
    arrival_iata VARCHAR(10),
    arrival_icao VARCHAR(10),
    arrival_terminal VARCHAR(10),
    arrival_gate VARCHAR(10),
    arrival_baggage VARCHAR(10),
    arrival_delay INT,
    arrival_scheduled VARCHAR(255),
    arrival_estimated VARCHAR(255),
    arrival_actual VARCHAR(255),
    arrival_estimated_runway VARCHAR(255),
    arrival_actual_runway VARCHAR(255),

    -- Campos embutidos (Embedded) de Flight
    flight_number VARCHAR(255),
    flight_iata VARCHAR(255),
    flight_icao VARCHAR(255),
    flight_codeshared BOOLEAN,

    -- Campos embutidos (Embedded) de Live
    live_updated VARCHAR(255),
    live_latitude DOUBLE,
    live_longitude DOUBLE,
    live_altitude DOUBLE,
    live_direction DOUBLE,
    live_speed_horizontal DOUBLE,
    live_speed_vertical DOUBLE,
    live_is_ground BOOLEAN,

    -- Chaves Estrangeiras para as entidades relacionadas
    airline_id BIGINT,
    aircraft_id BIGINT,
    departure_airport_id BIGINT,
    arrival_airport_id BIGINT,
    route_id BIGINT,

    -- Definição das constraints de Chave Estrangeira
    FOREIGN KEY (airline_id) REFERENCES tb_airline(id),
    FOREIGN KEY (aircraft_id) REFERENCES tb_aircraft(id),
    FOREIGN KEY (departure_airport_id) REFERENCES tb_airport(id),
    FOREIGN KEY (arrival_airport_id) REFERENCES tb_airport(id),
    FOREIGN KEY (route_id) REFERENCES tb_route(id)
);

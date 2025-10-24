-- Inserindo 50 rotas pré-definidas com direção calculada em cada waypoint.

-- Rota 1: Ponte Aérea (Congonhas -> Santos Dumont)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (1, 'CGH-SDU', 41, 44);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(1, -23.626100, -46.656400, 77.53), (1, -23.497413, -45.992075, 76.53), (1, -23.368027, -45.328350, 75.33), (1, -23.237940, -44.665225, 74.02), (1, -23.107153, -44.002700, 69.80), (1, -22.910500, -43.163100, 69.80);

-- Rota 2: Guarulhos -> Recife
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (2, 'GRU-REC', 40, 55);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(2, -23.435600, -46.473100, 39.81), (2, -21.499683, -44.750150, 40.08), (2, -19.559950, -43.034200, 40.54), (2, -17.615200, -41.326250, 41.34), (2, -15.664233, -39.627300, 42.82), (2, -13.705850, -37.938350, 45.73), (2, -11.738850, -36.260400, 49.99), (2, -9.762033, -34.594450, 15.30), (2, -8.126400, -34.923100, 15.30);

-- Rota 3: Brasília -> Salvador
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (3, 'BSB-SSA', 50, 54);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(3, -15.869200, -47.917200, 68.99), (3, -15.281867, -45.973700, 69.21), (3, -14.690800, -44.033700, 69.57), (3, -14.096000, -42.097200, 70.21), (3, -13.497467, -40.164200, 100.23), (3, -12.911100, -38.331100, 100.23);

-- Rota 4: Campinas -> Porto Alegre
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (4, 'VCP-POA', 42, 49);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(4, -23.006900, -47.134400, 153.94), (4, -24.406380, -47.882420, 163.63), (4, -25.801660, -48.629560, 163.66), (4, -27.192540, -49.375620, 163.70), (4, -28.578820, -50.120400, 164.08), (4, -29.994400, -51.171400, 164.08);

-- Rota 5: Manaus -> Belém
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (5, 'MAO-BEL', 64, 63);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(5, -3.038600, -60.049400, 95.34), (5, -2.766350, -57.185650, 95.36), (5, -2.492500, -54.323500, 95.40), (5, -2.217050, -51.462950, 95.45), (5, -1.940000, -48.604000, 109.83), (5, -1.379200, -48.476400, 109.83);

-- Rota 6: Guarulhos -> Miami
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (6, 'GRU-MIA', 40, 9);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(6, -23.435600, -46.473100, 334.33), (6, -15.596100, -56.096700, 350.36), (6, -2.800000, -60.016700, 331.02), (6, 4.711000, -74.146900, 24.33), (6, 12.500000, -70.066700, 334.69), (6, 25.793300, -80.290600, 334.69);

-- Rota 7: Galeão -> Lisboa
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (7, 'GIG-LIS', 43, 25);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(7, -22.808900, -43.243600, 21.36), (7, -12.971100, -38.510800, 29.54), (7, 0.000000, -30.000000, 28.50), (7, 16.266700, -22.933300, 36.56), (7, 28.100000, -15.416700, 21.84), (7, 38.774200, -9.134200, 21.84);

-- Rota 8: Guarulhos -> Paris (CDG)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (8, 'GRU-CDG', 40, 15);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(8, -23.435600, -46.473100, 24.62), (8, -12.971100, -38.510800, 29.54), (8, 0.000000, -30.000000, 28.50), (8, 16.266700, -22.933300, 36.56), (8, 28.100000, -15.416700, 20.84), (8, 43.700000, -1.250000, 20.25), (8, 49.012800, 2.550000, 20.25);

-- Rota 9: Galeão -> Dubai
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (9, 'GIG-DXB', 43, 26);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(9, -22.808900, -43.243600, 56.66), (9, -1.283333, -20.000000, 63.80), (9, 10.000000, 5.000000, 75.96), (9, 15.000000, 25.000000, 71.57), (9, 20.000000, 40.000000, 64.91), (9, 25.252800, 55.364400, 64.91);

-- Rota 10: JFK -> Heathrow
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (10, 'JFK-LHR', 6, 14);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(10, 40.639700, -73.778900, 63.78), (10, 45.000000, -60.000000, 60.10), (10, 50.000000, -40.000000, 76.65), (10, 52.000000, -20.000000, 86.82), (10, 51.500000, -10.000000, 92.14), (10, 51.470600, -0.461900, 92.14);

-- Rota 11: Los Angeles -> Tóquio (Haneda)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (11, 'LAX-HND', 5, 27);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(11, 33.942500, -118.408100, 305.82), (11, 40.000000, -135.000000, 302.47), (11, 50.000000, -160.000000, 287.97), (11, 55.000000, 175.000000, 237.12), (11, 45.000000, 155.000000, 241.69), (11, 35.552200, 139.779700, 241.69);

-- Rota 12: Dubai -> Singapura
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (12, 'DXB-SIN', 26, 32);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(12, 25.252800, 55.364400, 102.32), (12, 24.000000, 65.000000, 115.19), (12, 20.000000, 75.000000, 115.19), (12, 15.000000, 85.000000, 115.19), (12, 10.000000, 95.000000, 116.48), (12, 1.364400, 103.991500, 116.48);

-- Rota 13: Frankfurt -> Hong Kong
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (13, 'FRA-HKG', 17, 30);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(13, 50.033300, 8.570600, 81.39), (13, 52.516700, 20.000000, 84.81), (13, 55.750000, 40.000000, 90.69), (13, 55.000000, 70.000000, 123.69), (13, 45.000000, 95.000000, 142.10), (13, 30.000000, 110.000000, 110.87), (13, 22.308900, 113.914600, 110.87);

-- Rota 14: Sydney -> Dallas
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (14, 'SYD-DFW', 34, 2);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(14, -33.946100, 151.177200, 39.54), (14, -20.000000, 170.000000, 48.06), (14, 0.000000, -160.000000, 56.44), (14, 20.000000, -130.000000, 71.57), (14, 30.000000, -110.000000, 79.52), (14, 32.896900, -97.038100, 79.52);

-- Rota 15: Heathrow -> Amsterdam
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (15, 'LHR-AMS', 14, 16);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(15, 51.470600, -0.461900, 82.20), (15, 51.750000, 1.500000, 75.96), (15, 52.000000, 3.000000, 77.26), (15, 52.308600, 4.763900, 77.26);

-- Rota 16: Dallas (DFW) -> Chicago (ORD)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (16, 'DFW-ORD', 2, 4);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(16, 32.896900, -97.038100, 38.31), (16, 35.000000, -94.000000, 31.93), (16, 38.000000, -91.000000, 24.11), (16, 41.978600, -87.904700, 24.11);

-- Rota 17: São Paulo (CGH) -> Brasília (BSB)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (17, 'CGH-BSB', 41, 50);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(17, -23.626100, -46.656400, 351.49), (17, -21.600000, -47.000000, 351.05), (17, -19.000000, -47.500000, 354.34), (17, -15.869200, -47.917200, 354.34);

-- Rota 18: Paris (CDG) -> Roma (FCO)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (18, 'CDG-FCO', 15, 20);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(18, 49.012800, 2.550000, 137.98), (18, 47.000000, 6.000000, 135.00), (18, 45.000000, 9.000000, 142.17), (18, 41.800300, 12.238900, 142.17);

-- Rota 19: Belo Horizonte (CNF) -> Fortaleza (FOR)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (19, 'CNF-FOR', 45, 56);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(19, -19.624400, -43.971900, 17.57), (19, -15.000000, -42.000000, 13.59), (19, -10.000000, -40.000000, 21.64), (19, -3.776100, -38.532500, 21.64);

-- Rota 20: Dubai (DXB) -> Londres (LHR)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (20, 'DXB-LHR', 26, 14);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(20, 25.252800, 55.364400, 303.88), (20, 30.000000, 48.000000, 308.20), (20, 35.000000, 35.000000, 305.53), (20, 40.000000, 25.000000, 309.81), (20, 45.000000, 15.000000, 315.00), (20, 50.000000, 5.000000, 302.50), (20, 51.470600, -0.461900, 302.50);

-- Rota 21: São Paulo (GRU) -> Florianópolis (FLN)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (21, 'GRU-FLN', 40, 48);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(21, -23.435600, -46.473100, 169.56), (21, -24.500000, -47.000000, 163.63), (21, -26.000000, -48.000000, 165.71), (21, -27.670500, -48.552500, 165.71);

-- Rota 22: Los Angeles (LAX) -> Las Vegas (LAS)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (22, 'LAX-LAS', 5, 8);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(22, 33.942500, -118.408100, 60.03), (22, 35.000000, -116.500000, 58.74), (22, 36.080000, -115.152200, 58.74);

-- Rota 23: Miami (MIA) -> Orlando (MCO)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (23, 'MIA-MCO', 9, 7);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(23, 25.793300, -80.290600, 345.98), (23, 27.000000, -80.800000, 347.88), (23, 28.429400, -81.309000, 347.88);

-- Rota 24: Rio de Janeiro (GIG) -> Salvador (SSA)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (24, 'GIG-SSA', 43, 54);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(24, -22.808900, -43.243600, 30.65), (24, -20.000000, -41.500000, 19.33), (24, -16.500000, -40.000000, 27.20), (24, -12.911100, -38.331100, 27.20);

-- Rota 25: Amsterdam (AMS) -> Barcelona (BCN)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (25, 'AMS-BCN', 16, 19);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(25, 52.308600, 4.763900, 191.07), (25, 50.000000, 3.500000, 191.35), (25, 46.000000, 2.500000, 184.28), (25, 41.297100, 2.078400, 184.28);

-- Rota 26: Curitiba (CWB) -> Campo Grande (CGR)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (26, 'CWB-CGR', 47, 53);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(26, -25.528300, -49.175800, 305.51), (26, -24.000000, -51.000000, 310.89), (26, -22.000000, -53.000000, 307.72), (26, -20.469400, -54.673300, 307.72);

-- Rota 27: Toronto (YYZ) -> San Francisco (SFO)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (27, 'YYZ-SFO', 11, 10);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(27, 43.677200, -79.630600, 283.74), (27, 48.000000, -90.000000, 282.86), (27, 50.000000, -105.000000, 251.65), (27, 37.618900, -122.375000, 251.65);

-- Rota 28: Madri (MAD) -> Munique (MUC)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (28, 'MAD-MUC', 18, 21);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(28, 40.493600, -3.566800, 49.69), (28, 44.000000, 2.000000, 54.78), (28, 47.000000, 8.000000, 68.30), (28, 48.353800, 11.786100, 68.30);

-- Rota 29: São Paulo (GRU) -> Doha (DOH)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (29, 'GRU-DOH', 40, 36);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(29, -23.435600, -46.473100, 50.70), (29, -10.000000, -25.000000, 59.93), (29, 5.000000, 0.000000, 63.43), (29, 15.000000, 25.000000, 69.96), (29, 25.273100, 51.608100, 69.96);

-- Rota 30: Hong Kong (HKG) -> Sydney (SYD)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (30, 'HKG-SYD', 30, 34);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(30, 22.308900, 113.914600, 169.54), (30, 10.000000, 120.000000, 158.43), (30, -5.000000, 130.000000, 155.67), (30, -20.000000, 140.000000, 148.97), (30, -33.946100, 151.177200, 148.97);

-- Rota 31: São Paulo (GRU) -> Manaus (MAO)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (31, 'GRU-MAO', 40, 64);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(31, -23.435600, -46.473100, 318.57), (31, -19.000000, -52.000000, 329.81), (31, -12.000000, -56.000000, 334.33), (31, -3.038600, -60.049400, 334.33);

-- Rota 32: Recife (REC) -> Lisboa (LIS)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (32, 'REC-LIS', 55, 25);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(32, -8.126400, -34.923100, 11.13), (32, 5.000000, -30.000000, 21.80), (32, 20.000000, -20.000000, 22.75), (32, 38.774200, -9.134200, 22.75);

-- Rota 33: Chicago (ORD) -> San Francisco (SFO)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (33, 'ORD-SFO', 4, 10);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(33, 41.978600, -87.904700, 274.65), (33, 41.000000, -100.000000, 260.66), (33, 39.000000, -112.000000, 260.85), (33, 37.618900, -122.375000, 260.85);

-- Rota 34: Brasília (BSB) -> Cuiabá (CGB)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (34, 'BSB-CGB', 50, 52);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(34, -15.869200, -47.917200, 273.43), (34, -15.750000, -52.000000, 272.24), (34, -15.652900, -56.117200, 272.24);

-- Rota 35: Istambul (IST) -> Dubai (DXB)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (35, 'IST-DXB', 13, 26);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(35, 41.275300, 28.751900, 137.98), (35, 38.000000, 38.000000, 130.63), (35, 32.000000, 48.000000, 126.83), (35, 25.252800, 55.364400, 126.83);

-- Rota 36: Guarulhos (GRU) -> Porto Alegre (POA)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (36, 'GRU-POA', 40, 49);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(36, -23.435600, -46.473100, 206.58), (36, -26.000000, -48.500000, 218.03), (36, -28.000000, -50.000000, 222.18), (36, -29.994400, -51.171400, 222.18);

-- Rota 37: Dubai (DXB) -> Atlanta (ATL)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (37, 'DXB-ATL', 26, 1);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(37, 25.252800, 55.364400, 303.40), (37, 40.000000, 30.000000, 310.89), (37, 50.000000, -10.000000, 275.63), (37, 45.000000, -50.000000, 248.58), (37, 33.636700, -84.428100, 248.58);

-- Rota 38: Nova York (JFK) -> Cidade do México (MEX)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (38, 'JFK-MEX', 6, 12);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(38, 40.639700, -73.778900, 225.29), (38, 35.000000, -85.000000, 225.00), (38, 28.000000, -95.000000, 230.13), (38, 19.436300, -99.072100, 230.13);

-- Rota 39: Porto Alegre (POA) -> Rio de Janeiro (SDU)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (39, 'POA-SDU', 49, 44);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(39, -29.994400, -51.171400, 48.06), (39, -27.500000, -48.000000, 40.91), (39, -25.000000, -45.000000, 41.34), (39, -22.910500, -43.163100, 41.34);

-- Rota 40: Londres (LHR) -> Zurique (ZRH)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (40, 'LHR-ZRH', 14, 24);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(40, 51.470600, -0.461900, 115.82), (40, 50.000000, 3.000000, 122.92), (40, 48.500000, 6.000000, 126.96), (40, 47.464700, 8.549200, 126.96);

-- Rota 41: Fortaleza (FOR) -> Manaus (MAO)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (41, 'FOR-MAO', 56, 64);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(41, -3.776100, -38.532500, 266.39), (41, -3.500000, -45.000000, 266.86), (41, -3.200000, -52.000000, 267.43), (41, -3.038600, -60.049400, 267.43);

-- Rota 42: Singapura (SIN) -> Sydney (SYD)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (42, 'SIN-SYD', 32, 34);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(42, 1.364400, 103.991500, 137.96), (42, -5.000000, 115.000000, 139.44), (42, -15.000000, 130.000000, 139.44), (42, -25.000000, 140.000000, 146.31), (42, -33.946100, 151.177200, 146.31);

-- Rota 43: São Paulo (GRU) -> Curitiba (CWB)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (43, 'GRU-CWB', 40, 47);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(43, -23.435600, -46.473100, 221.03), (43, -24.500000, -48.000000, 230.13), (43, -25.528300, -49.175800, 230.13);

-- Rota 44: Denver (DEN) -> Los Angeles (LAX)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (44, 'DEN-LAX', 3, 5);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(44, 39.861700, -104.673100, 245.89), (44, 37.000000, -111.000000, 251.04), (44, 34.000000, -117.000000, 264.44), (44, 33.942500, -118.408100, 264.44);

-- Rota 45: Tóquio (HND) -> Seul (ICN)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (45, 'HND-ICN', 27, 31);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(45, 35.552200, 139.779700, 279.16), (45, 35.000000, 135.000000, 290.35), (45, 36.000000, 130.000000, 288.58), (45, 37.469200, 126.450500, 288.58);

-- Rota 46: Salvador (SSA) -> Belo Horizonte (CNF)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (46, 'SSA-CNF', 54, 45);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(46, -12.911100, -38.331100, 219.06), (46, -15.000000, -40.000000, 225.00), (46, -17.500000, -42.000000, 226.79), (46, -19.624400, -43.971900, 226.79);

-- Rota 47: Lisboa (LIS) -> Madri (MAD)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (47, 'LIS-MAD', 25, 18);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(47, 38.774200, -9.134200, 78.69), (47, 39.500000, -6.000000, 75.96), (47, 40.493600, -3.566800, 75.96);

-- Rota 48: Miami (MIA) -> Cidade do México (MEX)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (48, 'MIA-MEX', 9, 12);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(48, 25.793300, -80.290600, 263.13), (48, 26.000000, -88.000000, 248.51), (48, 22.000000, -95.000000, 230.13), (48, 19.436300, -99.072100, 230.13);

-- Rota 49: Frankfurt (FRA) -> Istambul (IST)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (49, 'FRA-IST', 17, 13);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(49, 50.033300, 8.570600, 117.84), (49, 48.000000, 16.000000, 119.53), (49, 45.000000, 25.000000, 116.32), (49, 41.275300, 28.751900, 116.32);

-- Rota 50: Rio de Janeiro (GIG) -> Brasília (BSB)
INSERT INTO tb_route (id, name, origin_airport_id, destination_airport_id) VALUES (50, 'GIG-BSB', 43, 50);
INSERT INTO tb_route_waypoints (route_id, latitude, longitude, direction) VALUES
(50, -22.808900, -43.243600, 319.49), (50, -20.500000, -45.000000, 323.13), (50, -18.000000, -46.500000, 328.75), (50, -15.869200, -47.917200, 328.75);
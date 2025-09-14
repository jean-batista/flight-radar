-- América do Norte
INSERT INTO tb_airport (name, iata, icao, latitude, longitude, geoname_id, timezone, gmt, phone_number, country_name, country_iso2, city_iata_code) VALUES
('Hartsfield-Jackson Atlanta Intl', 'ATL', 'KATL', 33.636700, -84.428100, NULL, 'America/New_York', -4, NULL, 'United States', 'US', 'ATL'),
('Dallas/Fort Worth Intl', 'DFW', 'KDFW', 32.896900, -97.038100, NULL, 'America/Chicago', -5, NULL, 'United States', 'US', 'DFW'),
('Denver Intl', 'DEN', 'KDEN', 39.861700, -104.673100, NULL, 'America/Denver', -6, NULL, 'United States', 'US', 'DEN'),
('O''Hare Intl', 'ORD', 'KORD', 41.978600, -87.904700, NULL, 'America/Chicago', -5, NULL, 'United States', 'US', 'ORD'),
('Los Angeles Intl', 'LAX', 'KLAX', 33.942500, -118.408100, NULL, 'America/Los_Angeles', -7, NULL, 'United States', 'US', 'LAX'),
('John F. Kennedy Intl', 'JFK', 'KJFK', 40.639700, -73.778900, NULL, 'America/New_York', -4, NULL, 'United States', 'US', 'JFK'),
('Orlando Intl', 'MCO', 'KMCO', 28.429400, -81.309000, NULL, 'America/New_York', -4, NULL, 'United States', 'US', 'MCO'),
('Harry Reid Intl', 'LAS', 'KLAS', 36.080000, -115.152200, NULL, 'America/Los_Angeles', -7, NULL, 'United States', 'US', 'LAS'),
('Miami Intl', 'MIA', 'KMIA', 25.793300, -80.290600, NULL, 'America/New_York', -4, NULL, 'United States', 'US', 'MIA'),
('San Francisco Intl', 'SFO', 'KSFO', 37.618900, -122.375000, NULL, 'America/Los_Angeles', -7, NULL, 'United States', 'US', 'SFO'),
('Toronto Pearson Intl', 'YYZ', 'CYYZ', 43.677200, -79.630600, NULL, 'America/Toronto', -4, NULL, 'Canada', 'CA', 'YYZ'),
('Mexico City Intl', 'MEX', 'MMMX', 19.436300, -99.072100, NULL, 'America/Mexico_City', -6, NULL, 'Mexico', 'MX', 'MEX'),

-- Europa

('Istanbul Airport', 'IST', 'LTFM', 41.275300, 28.751900, NULL, 'Europe/Istanbul', 3, NULL, 'Turkey', 'TR', 'IST'),
('London Heathrow', 'LHR', 'EGLL', 51.470600, -0.461900, NULL, 'Europe/London', 1, NULL, 'United Kingdom', 'GB', 'LHR'),
('Charles de Gaulle', 'CDG', 'LFPG', 49.012800, 2.550000, NULL, 'Europe/Paris', 2, NULL, 'France', 'FR', 'CDG'),
('Amsterdam Schiphol', 'AMS', 'EHAM', 52.308600, 4.763900, NULL, 'Europe/Amsterdam', 2, NULL, 'Netherlands', 'NL', 'AMS'),
('Frankfurt am Main', 'FRA', 'EDDF', 50.033300, 8.570600, NULL, 'Europe/Berlin', 2, NULL, 'Germany', 'DE', 'FRA'),
('Adolfo Suárez Madrid–Barajas', 'MAD', 'LEMD', 40.493600, -3.566800, NULL, 'Europe/Madrid', 2, NULL, 'Spain', 'ES', 'MAD'),
('Barcelona–El Prat', 'BCN', 'LEBL', 41.297100, 2.078400, NULL, 'Europe/Madrid', 2, NULL, 'Spain', 'ES', 'BCN'),
('Leonardo da Vinci–Fiumicino', 'FCO', 'LIRF', 41.800300, 12.238900, NULL, 'Europe/Rome', 2, NULL, 'Italy', 'IT', 'FCO'),
('Munich Airport', 'MUC', 'EDDM', 48.353800, 11.786100, NULL, 'Europe/Berlin', 2, NULL, 'Germany', 'DE', 'MUC'),
('London Gatwick', 'LGW', 'EGKK', 51.148100, -0.190300, NULL, 'Europe/London', 1, NULL, 'United Kingdom', 'GB', 'LGW'),
('Dublin Airport', 'DUB', 'EIDW', 53.421400, -6.270100, NULL, 'Europe/Dublin', 1, NULL, 'Ireland', 'IE', 'DUB'),
('Zurich Airport', 'ZRH', 'LSZH', 47.464700, 8.549200, NULL, 'Europe/Zurich', 2, NULL, 'Switzerland', 'CH', 'ZRH'),
('Lisbon Humberto Delgado', 'LIS', 'LPPT', 38.774200, -9.134200, NULL, 'Europe/Lisbon', 1, NULL, 'Portugal', 'PT', 'LIS'),

-- Ásia e Oceania

('Dubai Intl', 'DXB', 'OMDB', 25.252800, 55.364400, NULL, 'Asia/Dubai', 4, NULL, 'United Arab Emirates', 'AE', 'DXB'),
('Tokyo Haneda Intl', 'HND', 'RJTT', 35.552200, 139.779700, NULL, 'Asia/Tokyo', 9, NULL, 'Japan', 'JP', 'HND'),
('Indira Gandhi Intl', 'DEL', 'VIDP', 28.566500, 77.103100, NULL, 'Asia/Kolkata', 5, NULL, 'India', 'IN', 'DEL'),
('Guangzhou Baiyun Intl', 'CAN', 'ZGGG', 23.392400, 113.298800, NULL, 'Asia/Shanghai', 8, NULL, 'China', 'CN', 'CAN'),
('Hong Kong Intl', 'HKG', 'VHHH', 22.308900, 113.914600, NULL, 'Asia/Hong_Kong', 8, NULL, 'Hong Kong', 'HK', 'HKG'),
('Incheon Intl', 'ICN', 'RKSI', 37.469200, 126.450500, NULL, 'Asia/Seoul', 9, NULL, 'South Korea', 'KR', 'ICN'),
('Singapore Changi', 'SIN', 'WSSS', 1.364400, 103.991500, NULL, 'Asia/Singapore', 8, NULL, 'Singapore', 'SG', 'SIN'),
('Suvarnabhumi Airport', 'BKK', 'VTBS', 13.681100, 100.747500, NULL, 'Asia/Bangkok', 7, NULL, 'Thailand', 'TH', 'BKK'),
('Sydney Kingsford Smith', 'SYD', 'YSSY', -33.946100, 151.177200, NULL, 'Australia/Sydney', 10, NULL, 'Australia', 'AU', 'SYD'),
('Melbourne Airport', 'MEL', 'YMML', -37.673300, 144.843300, NULL, 'Australia/Melbourne', 10, NULL, 'Australia', 'AU', 'MEL'),
('Hamad Intl', 'DOH', 'OTHH', 25.273100, 51.608100, NULL, 'Asia/Qatar', 3, NULL, 'Qatar', 'QA', 'DOH'),
('King Abdulaziz Intl', 'JED', 'OEJN', 21.679400, 39.156400, NULL, 'Asia/Riyadh', 3, NULL, 'Saudi Arabia', 'SA', 'JED'),
('Shanghai Pudong Intl', 'PVG', 'ZSPD', 31.143300, 121.805300, NULL, 'Asia/Shanghai', 8, NULL, 'China', 'CN', 'PVG'),
('Kuala Lumpur Intl', 'KUL', 'WMKK', 2.745600, 101.709900, NULL, 'Asia/Kuala_Lumpur', 8, NULL, 'Malaysia', 'MY', 'KUL'),

-- Brasil - Sudeste

('São Paulo/Guarulhos', 'GRU', 'SBGR', -23.435600, -46.473100, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'GRU'),
('São Paulo/Congonhas', 'CGH', 'SBSP', -23.626100, -46.656400, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'CGH'),
('Viracopos/Campinas', 'VCP', 'SBKP', -23.006900, -47.134400, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'VCP'),
('Rio de Janeiro/Galeão', 'GIG', 'SBGL', -22.808900, -43.243600, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'GIG'),
('Rio de Janeiro/Santos Dumont', 'SDU', 'SBRJ', -22.910500, -43.163100, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'SDU'),
('Belo Horizonte/Confins', 'CNF', 'SBCF', -19.624400, -43.971900, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'CNF'),
('Vitória/Eurico de Aguiar Salles', 'VIX', 'SBVT', -20.258100, -40.286400, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'VIX'),

-- Brasil - Sul

('Curitiba/Afonso Pena', 'CWB', 'SBCT', -25.528300, -49.175800, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'CWB'),
('Florianópolis/Hercílio Luz', 'FLN', 'SBFL', -27.670500, -48.552500, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'FLN'),
('Porto Alegre/Salgado Filho', 'POA', 'SBPA', -29.994400, -51.171400, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'POA'),

-- Brasil - Centro-Oeste

('Brasília/Juscelino Kubitschek', 'BSB', 'SBBR', -15.869200, -47.917200, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'BSB'),
('Goiânia/Santa Genoveva', 'GYN', 'SBGO', -16.632200, -49.220800, NULL, 'America/Sao_Paulo', -3, NULL, 'Brazil', 'BR', 'GYN'),
('Cuiabá/Marechal Rondon', 'CGB', 'SBCY', -15.652900, -56.117200, NULL, 'America/Cuiaba', -4, NULL, 'Brazil', 'BR', 'CGB'),
('Campo Grande Intl', 'CGR', 'SBCG', -20.469400, -54.673300, NULL, 'America/Campo_Grande', -4, NULL, 'Brazil', 'BR', 'CGR'),

-- Brasil - Nordeste

('Salvador/Dep. Luís Eduardo Magalhães', 'SSA', 'SBSV', -12.911100, -38.331100, NULL, 'America/Bahia', -3, NULL, 'Brazil', 'BR', 'SSA'),
('Recife/Guararapes-Gilberto Freyre', 'REC', 'SBRF', -8.126400, -34.923100, NULL, 'America/Recife', -3, NULL, 'Brazil', 'BR', 'REC'),
('Fortaleza/Pinto Martins', 'FOR', 'SBFZ', -3.776100, -38.532500, NULL, 'America/Fortaleza', -3, NULL, 'Brazil', 'BR', 'FOR'),
('Natal/Gov. Aluízio Alves', 'NAT', 'SBSG', -5.767600, -35.365300, NULL, 'America/Fortaleza', -3, NULL, 'Brazil', 'BR', 'NAT'),
('Maceió/Zumbi dos Palmares', 'MCZ', 'SBMO', -9.510700, -35.791600, NULL, 'America/Maceio', -3, NULL, 'Brazil', 'BR', 'MCZ'),
('João Pessoa/Pres. Castro Pinto', 'JPA', 'SBJP', -7.148300, -34.950600, NULL, 'America/Fortaleza', -3, NULL, 'Brazil', 'BR', 'JPA'),
('Aracaju/Santa Maria', 'AJU', 'SBAR', -10.984000, -37.070300, NULL, 'America/Maceio', -3, NULL, 'Brazil', 'BR', 'AJU'),
('São Luís/Mal. Cunha Machado', 'SLZ', 'SBSL', -2.585500, -44.234300, NULL, 'America/Fortaleza', -3, NULL, 'Brazil', 'BR', 'SLZ'),
('Teresina/Sen. Petrônio Portella', 'THE', 'SBTE', -5.060100, -42.823600, NULL, 'America/Fortaleza', -3, NULL, 'Brazil', 'BR', 'THE'),

-- Brasil - Norte

('Belém/Val de Cans', 'BEL', 'SBBE', -1.379200, -48.476400, NULL, 'America/Belem', -3, NULL, 'Brazil', 'BR', 'BEL'),
('Manaus/Eduardo Gomes', 'MAO', 'SBEG', -3.038600, -60.049400, NULL, 'America/Manaus', -4, NULL, 'Brazil', 'BR', 'MAO'),
('Rio Branco/Plácido de Castro', 'RBR', 'SBRB', -9.868700, -67.895300, NULL, 'America/Rio_Branco', -5, NULL, 'Brazil', 'BR', 'RBR'),
('Porto Velho/Gov. Jorge Teixeira', 'PVH', 'SBPV', -8.706100, -63.902200, NULL, 'America/Porto_Velho', -4, NULL, 'Brazil', 'BR', 'PVH'),
('Boa Vista/Atlas Brasil Cantanhede', 'BVB', 'SBBV', 2.844700, -60.692200, NULL, 'America/Boa_Vista', -4, NULL, 'Brazil', 'BR', 'BVB'),
('Macapá Intl', 'MCP', 'SBMQ', 0.050700, -51.071000, NULL, 'America/Belem', -3, NULL, 'Brazil', 'BR', 'MCP'),
('Palmas/Brig. Lysias Rodrigues', 'PMW', 'SBPJ', -10.290600, -48.358100, NULL, 'America/Araguaina', -3, NULL, 'Brazil', 'BR', 'PMW');


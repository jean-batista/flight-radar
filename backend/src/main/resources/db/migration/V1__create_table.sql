-- Criando a tabela de pessoas
CREATE TABLE IF NOT EXISTS tb_person (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    birth_date DATE,
    email VARCHAR(255) UNIQUE
);
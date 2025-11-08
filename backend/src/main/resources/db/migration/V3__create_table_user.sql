-- Tabela principal para armazenar os dados de autenticacao do usuario
CREATE TABLE IF NOT EXISTS tb_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    person_id BIGINT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES tb_person(id)
);

-- Tabela para armazenar a lista de Roles
CREATE TABLE IF NOT EXISTS tb_user_roles (
    user_id BIGINT NOT NULL,
    roles TINYINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_user(id)
);
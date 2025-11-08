-- Insere roles aos usuarios cadastrados

-- 0 = USER
-- 1 = ADMIN
INSERT INTO tb_user_roles (user_id, roles) VALUES
(1, 0),
(1, 1),
(2, 0),
(2, 1),
(3, 0),
(3, 1),
(4, 0),
(4, 1);
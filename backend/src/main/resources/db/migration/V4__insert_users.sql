-- Inserindo usuarios no banco
INSERT INTO tb_user (username, password, person_id)
VALUES (
    'arthur@email.com', 
    '{pbkdf2}d991f706beb55c6ea5903b5714a2b5c0e498ab75fbd22945c16be1a70420489d6d8f2fb9542d2359',
    1
),
(
    'joao@email.com', 
    '{pbkdf2}d991f706beb55c6ea5903b5714a2b5c0e498ab75fbd22945c16be1a70420489d6d8f2fb9542d2359',
    2
),
(
    'claudio@email.com', 
    '{pbkdf2}d991f706beb55c6ea5903b5714a2b5c0e498ab75fbd22945c16be1a70420489d6d8f2fb9542d2359',
    3
),
(
    'jean@email.com', 
    '{pbkdf2}d991f706beb55c6ea5903b5714a2b5c0e498ab75fbd22945c16be1a70420489d6d8f2fb9542d2359',
    4
);
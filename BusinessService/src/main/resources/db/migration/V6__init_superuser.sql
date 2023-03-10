INSERT INTO passenger (id, email, password, username, passport, firstname, lastname, birthdate, is_active)
VALUES (1,
        'sasha.pinsk2003@gmail.com',
        '$2a$10$H0y6itTq1A2bPK8zX9YNcuLN20wH2iz1w8AtU/HpghtBsuyRLuHb.',
        'babuba123',
        '21345678AB',
        'alex',
        'kaminskiy',
        '2023-03-10',
        true);

INSERT INTO passenger_roles (passenger_id, role)
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_MANAGER'),
       (1, 'ROLE_PASSENGER');
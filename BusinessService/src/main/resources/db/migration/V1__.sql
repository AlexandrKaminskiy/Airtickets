CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE airport
(
    id      INT NOT NULL,
    name    VARCHAR(40),
    country VARCHAR(40),
    town    VARCHAR(40),
    CONSTRAINT pk_airport PRIMARY KEY (id)
);

CREATE TABLE company
(
    id           INT NOT NULL,
    company_name VARCHAR(40),
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE passenger
(
    id        INT NOT NULL,
    email     VARCHAR(100),
    password  VARCHAR(100),
    username  VARCHAR(20),
    passport  VARCHAR(20),
    firstname VARCHAR(30),
    lastname  VARCHAR(30),
    birthdate date,
    CONSTRAINT pk_passenger PRIMARY KEY (id)
);

CREATE TABLE passenger_roles
(
    passenger_id INT NOT NULL,
    roles        VARCHAR(10)
);

CREATE TABLE ticket
(
    id             INT NOT NULL,
    from_id        INT,
    to_id          INT,
    time_departure TIMESTAMP WITHOUT TIME ZONE,
    time_arrive    TIMESTAMP WITHOUT TIME ZONE,
    price          DECIMAL,
    company_id     INT,
    passenger_id   INT,
    CONSTRAINT pk_ticket PRIMARY KEY (id)
);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_FROM FOREIGN KEY (from_id) REFERENCES airport (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_PASSENGER FOREIGN KEY (passenger_id) REFERENCES passenger (id);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_TO FOREIGN KEY (to_id) REFERENCES airport (id);

ALTER TABLE passenger_roles
    ADD CONSTRAINT fk_passenger_roles_on_passenger FOREIGN KEY (passenger_id) REFERENCES passenger (id);
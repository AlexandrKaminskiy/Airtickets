CREATE TABLE airport
(
    id      SERIAL NOT NULL,
    name    VARCHAR(40),
    country VARCHAR(40),
    town    VARCHAR(40),
    CONSTRAINT pk_airport PRIMARY KEY (id)
);

CREATE TABLE passenger
(
    id        SERIAL NOT NULL,
    email     VARCHAR(100),
    password  VARCHAR(100),
    username  VARCHAR(20),
    passport  VARCHAR(20),
    firstname VARCHAR(30),
    lastname  VARCHAR(30),
    birthdate date,
    CONSTRAINT pk_passenger PRIMARY KEY (id)
);

CREATE TABLE flight
(
    id             SERIAL NOT NULL,
    from_id        INTEGER,
    to_id          INTEGER,
    time_departure TIMESTAMP with time zone,
    time_arrive    TIMESTAMP with time zone,
    CONSTRAINT pk_flight      PRIMARY KEY (id),
    CONSTRAINT fk_flight_from FOREIGN KEY (from_id) REFERENCES airport (id) ON DELETE SET NULL,
    CONSTRAINT fk_flight_to   FOREIGN KEY (to_id) REFERENCES airport (id) ON DELETE SET NULL
);

CREATE TABLE passenger_roles
(
    passenger_id SERIAL NOT NULL,
    role         VARCHAR(10),
    CONSTRAINT fk_passenger_roles_on_passenger FOREIGN KEY (passenger_id) REFERENCES passenger (id) ON DELETE CASCADE
);

CREATE TABLE ticket
(
    id           SERIAL NOT NULL,
    price        DECIMAL,
    seat_no      INTEGER,
    flight_id    INTEGER,
    passenger_id INTEGER,
    CONSTRAINT pk_ticket              PRIMARY KEY (id),
    CONSTRAINT fk_ticket_on_flight    FOREIGN KEY (flight_id) REFERENCES flight (id) ON DELETE CASCADE,
    CONSTRAINT fk_ticket_on_passenger FOREIGN KEY (passenger_id) REFERENCES passenger (id) ON DELETE SET NULL
);

ALTER SEQUENCE airport_id_seq RENAME TO airport_seq;
ALTER SEQUENCE flight_id_seq RENAME TO flight_seq;
ALTER SEQUENCE passenger_id_seq RENAME TO passenger_seq;
ALTER SEQUENCE ticket_id_seq RENAME TO ticket_seq;
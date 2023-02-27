CREATE TABLE jwt_holder (
    id SERIAL NOT NULL,
    access_token VARCHAR,
    refresh_token VARCHAR,
    passenger_id INTEGER,
    CONSTRAINT pk_jwt_holder PRIMARY KEY (id),
    CONSTRAINT fk_passenger_id FOREIGN KEY (passenger_id) REFERENCES passenger (id) ON DELETE CASCADE
);
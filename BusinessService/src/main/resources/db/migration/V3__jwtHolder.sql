CREATE TABLE jwt_holder (
    id SERIAL NOT NULL,
    access_token VARCHAR(255),
    refresh_token VARCHAR(255),
    passenger_id INTEGER,
    CONSTRAINT pk_jwt_holder PRIMARY KEY (id),
    CONSTRAINT fk_passenger_id FOREIGN KEY (passenger_id) REFERENCES passenger (id) ON DELETE CASCADE
);
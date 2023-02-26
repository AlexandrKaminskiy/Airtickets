CREATE TABLE activator_link (
    id SERIAL NOT NULL,
    uuid VARCHAR,
    passenger_id INTEGER,
    CONSTRAINT fk_passenger_id FOREIGN KEY (passenger_id) REFERENCES passenger (id) ON DELETE CASCADE
);

ALTER TABLE passenger
ADD COLUMN is_active bool;
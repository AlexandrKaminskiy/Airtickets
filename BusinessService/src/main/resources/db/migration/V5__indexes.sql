ALTER TABLE passenger
ADD CONSTRAINT passenger_unique_email UNIQUE(email),
ADD CONSTRAINT passenger_unique_username UNIQUE(username);

CREATE INDEX airport_town_index    ON airport (town);
CREATE INDEX airport_name_index    ON airport (name);
CREATE INDEX airport_country_index ON airport (country);

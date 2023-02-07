CREATE SEQUENCE IF NOT EXISTS airport_id_seq;
ALTER TABLE airport
    ALTER COLUMN id SET NOT NULL;
ALTER TABLE airport
    ALTER COLUMN id SET DEFAULT nextval('airport_id_seq');

ALTER SEQUENCE airport_id_seq OWNED BY airport.id;

CREATE SEQUENCE IF NOT EXISTS company_id_seq;
ALTER TABLE company
    ALTER COLUMN id SET NOT NULL;
ALTER TABLE company
    ALTER COLUMN id SET DEFAULT nextval('company_id_seq');

ALTER SEQUENCE company_id_seq OWNED BY company.id;

CREATE SEQUENCE IF NOT EXISTS passenger_id_seq;
ALTER TABLE passenger
    ALTER COLUMN id SET NOT NULL;
ALTER TABLE passenger
    ALTER COLUMN id SET DEFAULT nextval('passenger_id_seq');

ALTER SEQUENCE passenger_id_seq OWNED BY passenger.id;

CREATE SEQUENCE IF NOT EXISTS ticket_id_seq;
ALTER TABLE ticket
    ALTER COLUMN id SET NOT NULL;
ALTER TABLE ticket
    ALTER COLUMN id SET DEFAULT nextval('ticket_id_seq');

ALTER SEQUENCE ticket_id_seq OWNED BY ticket.id;
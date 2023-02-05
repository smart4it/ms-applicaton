CREATE TABLE hh_counter
(
    id    uuid DEFAULT gen_random_uuid(),
    count INTEGER,
    text  VARCHAR(255),
    date  date,
    time  time,
    CONSTRAINT pk_hh_counter PRIMARY KEY (id)
);

CREATE INDEX idx_hh_counter_date ON hh_counter(date);
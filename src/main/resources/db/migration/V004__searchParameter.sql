CREATE TABLE IF NOT EXISTS searchParameters
(
    id VARCHAR(25) PRIMARY KEY,
    text VARCHAR(50),
    experience_id VARCHAR(50),
    employment_id VARCHAR(50),
    schedule_id VARCHAR(50),
    created timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc'),
    updated timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc'),
    FOREIGN KEY (experience_id) REFERENCES experience (id),
    FOREIGN KEY (employment_id) REFERENCES employment (id),
    FOREIGN KEY (schedule_id) REFERENCES schedule (id)
    );
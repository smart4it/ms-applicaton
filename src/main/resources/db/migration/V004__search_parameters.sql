CREATE TABLE IF NOT EXISTS search_parameters
(
    id            uuid      DEFAULT gen_random_uuid(),
    text          VARCHAR(25),
    experience_id VARCHAR(25),
    employment_id VARCHAR(25),
    schedule_id   VARCHAR(25),
    created       timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc'),
    updated       timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc'),
    CONSTRAINT search_parameters_experience_fkey
        FOREIGN KEY (experience_id) REFERENCES experience (id),
    CONSTRAINT search_parameters_employment_fkey
        FOREIGN KEY (employment_id) REFERENCES employment (id),
    CONSTRAINT search_parameters_schedule_fkey
        FOREIGN KEY (schedule_id) REFERENCES schedule (id)
);
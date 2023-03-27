CREATE TABLE IF NOT EXISTS schedule
(
    id VARCHAR(25) PRIMARY KEY,
    name VARCHAR(50),
    created timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc'),
    updated timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc')
    );

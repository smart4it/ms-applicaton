CREATE TABLE IF NOT EXISTS experience
(
    id VARCHAR(25) PRIMARY KEY,
    name VARCHAR(50),
    created timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc'),
    updated timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc')
);

CREATE OR REPLACE FUNCTION experience_update_updated()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated = now();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER experience_update_updated_trigger
    BEFORE UPDATE ON experience
    FOR EACH ROW
    EXECUTE FUNCTION experience_update_updated();
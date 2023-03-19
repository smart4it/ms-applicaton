CREATE TABLE IF NOT EXISTS employment
(
    id VARCHAR(25) PRIMARY KEY,
    name VARCHAR(50),
    created timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc'),
    updated timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc')
);

CREATE OR REPLACE FUNCTION employment_update_updated()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated = now();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER employment_update_updated_trigger
    BEFORE UPDATE ON employment
    FOR EACH ROW
    EXECUTE FUNCTION employment_update_updated();
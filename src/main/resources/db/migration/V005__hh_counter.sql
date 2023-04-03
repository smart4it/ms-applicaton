ALTER TABLE hh_counter add column search_params jsonb;

CREATE INDEX idx_hh_counter_search_params ON hh_counter USING GIN (search_params);

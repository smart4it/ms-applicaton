CREATE TABLE IF NOT EXISTS vacancies_search_parameters
(
    id            uuid      DEFAULT gen_random_uuid(),
    text          VARCHAR(50),
    disabled      boolean DEFAULT(false),
    created       timestamp with time zone DEFAULT (CURRENT_TIMESTAMP),
    updated       timestamp with time zone DEFAULT (CURRENT_TIMESTAMP)
    );

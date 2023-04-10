CREATE TABLE IF NOT EXISTS vacancy
(
    id            uuid      DEFAULT gen_random_uuid() primary key,
    vacancy_id    varchar(25) not null,
    text          json,
    created       timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc'),
    updated       timestamp DEFAULT (CURRENT_TIMESTAMP at time zone 'utc')
    );

CREATE TABLE IF NOT EXISTS vacancy_task
(
    id            uuid      DEFAULT gen_random_uuid() primary key,
    search_parameter  varchar(25) not null,
    start         timestamp with time zone,
    count         int not null,
    status        varchar(25) not null
    );


CREATE TABLE IF NOT EXISTS vacancy_subtask
(
    id            uuid      DEFAULT gen_random_uuid() primary key,
    page          int not null,
    done          boolean DEFAULT(false),
    vacancy_task_id uuid REFERENCES vacancy_task (id)
    );

CREATE TABLE IF NOT EXISTS vacancy_snapshot
(
    id           serial primary key,
    date         timestamp with time zone,
    vacancy_id   uuid REFERENCES vacancy (id),
    vacancy_task_id uuid REFERENCES vacancy_task (id)
    );
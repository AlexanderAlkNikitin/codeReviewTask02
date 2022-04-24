DROP TABLE IF EXISTS link;

CREATE TABLE IF NOT EXISTS link
(
    id            SERIAL PRIMARY KEY,
    short_link    varchar(20) not null unique,
    original_link varchar,
    created       timestamp
);

DROP TABLE IF EXISTS link_stat;

CREATE TABLE IF NOT EXISTS link_stat
(
    id            integer,
--     link_id       integer not null unique,
    rank          integer,
    request_count integer,
    updated       timestamp
);
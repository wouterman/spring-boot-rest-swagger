CREATE TABLE IF NOT EXISTS test.user
(
    id                     bigserial NOT NULL PRIMARY KEY,
    name                   varchar,
    role                   varchar,
    age                    int,
    modification_timestamp timestamp
);
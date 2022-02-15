DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS units;

CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    user_email VARCHAR(60) NOT NULL UNIQUE,
    user_username VARCHAR(40) NOT NULL UNIQUE,
    user_password VARCHAR(40) NOT NULL,
    user_active BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS sessions (
    session_id SERIAL PRIMARY KEY,
    session_text VARCHAR(60) NOT NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS units (
    unit_id SERIAL PRIMARY KEY,
    unit_user_id INTEGER NOT NULL,
    unit_text VARCHAR(60) NOT NULL DEFAULT '',
    unit_start TIMESTAMP NOT NULL,
    unit_end TIMESTAMP NOT NULL
);
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS user_groups;
DROP TABLE IF EXISTS pomodoros;
DROP TABLE IF EXISTS user_sessions;

CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    user_email VARCHAR(60) NOT NULL UNIQUE,
    user_username VARCHAR(40) NOT NULL UNIQUE,
    user_password VARCHAR(40) NOT NULL,
    user_active BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS groups (
    group_id SERIAL PRIMARY KEY,
    group_name VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user_groups (
    user_id INTEGER NOT NULL REFERENCES users (user_id),
    group_id INTEGER NOT NULL REFERENCES groups (group_id),
    PRIMARY KEY (user_id, group_id)
);

CREATE TABLE IF NOT EXISTS pomodoros (
    pomodoro_id SERIAL PRIMARY KEY,
    pomodoro_user_id INTEGER NOT NULL REFERENCES users (user_id),
    pomodoro_text VARCHAR(60) NOT NULL DEFAULT '',
    pomodoro_start TIMESTAMP NOT NULL,
    pomodoro_end TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS user_sessions ( 
    user_session_id SERIAL PRIMARY KEY,
    pomodoro_id INTEGER NOT NULL REFERENCES pomodoros (pomodoro_id),
    session_note VARCHAR(200) NOT NULL DEFAULT '',
    session_start_time TIMESTAMP NOT NULL,
    session_end_time TIMESTAMP NOT NULL,
    rating INTEGER NOT NULL DEFAULT 0
);
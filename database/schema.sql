DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS pomodoros;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS sessions_tbl;
DROP TABLE IF EXISTS units;
CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    user_email VARCHAR(60) NOT NULL UNIQUE,
    user_username VARCHAR(40) NOT NULL UNIQUE,
    user_password VARCHAR(40) NOT NULL,
    user_active BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS pomodoros (
    pomodoro_id SERIAL PRIMARY KEY,
    pomodoro_user_id INTEGER NOT NULL,
    pomodoro_text VARCHAR(60) NOT NULL DEFAULT '',
    pomodoro_start TIMESTAMP NOT NULL,
    pomodoro_end TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS groups (
  group_id INTEGER NOT NULL,
  group_name VARCHAR(60) NOT NULL UNIQUE,  
);

CREATE TABLE IF NOT EXISTS sessions_tbl ( 
    session_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    session_note VARCHAR(200) NOT NULL DEFAULT '',
    rating INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS units(
    unit_id INTEGER NOT NULL,
    session_id INTEGER NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL
);
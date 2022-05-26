DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS groups CASCADE;
DROP TABLE IF EXISTS user_groups CASCADE;
DROP TABLE IF EXISTS pomodoros CASCADE;
DROP TABLE IF EXISTS user_sessions CASCADE;

CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    user_email VARCHAR(60) NOT NULL UNIQUE,
    user_username VARCHAR(40) NOT NULL UNIQUE,
    user_password VARCHAR(80) NOT NULL,
    user_active BOOLEAN NOT NULL DEFAULT true
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

CREATE OR REPLACE VIEW leaderboard AS
SELECT users.user_username as username, CASE WHEN total IS NULL THEN 0 ELSE total END AS total 
FROM users LEFT JOIN (
    SELECT COUNT(pomodoro_id) AS total, pomodoro_user_id
    FROM pomodoros
    INNER JOIN users
    ON user_id=pomodoro_user_id
    GROUP BY pomodoro_user_id
) t
ON t.pomodoro_user_id=users.user_id
ORDER BY total DESC;
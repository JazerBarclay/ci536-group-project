-- user | user@quark.rocks | pass

INSERT INTO users (
    user_email,
    user_username,
    user_password
) VALUES (
    'user@quark.rocks',
    'user',
    '$2b$05$WWmkuO2ytn1wTtr/1E7ZqObInjA5aziSL8VIJ/XNd2olBdA7c8NMO'
);

INSERT INTO pomodoros (
    pomodoro_user_id,
    pomodoro_text,
    pomodoro_start,
    pomodoro_end

) VALUES (
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-010 08:46:32.252000',
    '2022-05-010 08:46:32.252000'
), (
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-011 08:46:32.252000',
    '2022-05-011 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-012 08:46:32.252000',
    '2022-05-012 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-013 08:46:32.252000',
    '2022-05-013 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-014 08:46:32.252000',
    '2022-05-014 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-015 08:46:32.252000',
    '2022-05-015 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-016 08:46:32.252000',
    '2022-05-016 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-017 08:46:32.252000',
    '2022-05-017 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-021 08:46:32.252000',
    '2022-05-021 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-023 08:46:32.252000',
    '2022-05-023 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-001 08:46:32.252000',
    '2022-05-001 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-002 08:46:32.252000',
    '2022-05-002 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-003 08:46:32.252000',
    '2022-05-003 08:46:32.252000'
),(
    (SELECT user_id FROM users WHERE user_email = 'user@quark.rocks'),
    'test',
    '2022-05-004 08:46:32.252000',
    '2022-05-004 08:46:32.252000'
);


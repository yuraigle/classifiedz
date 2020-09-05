DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS users_id_seq;

CREATE SEQUENCE users_id_seq INCREMENT 1 START 1;
CREATE TABLE users
(
    id         integer      NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    email      varchar(100) NOT NULL,
    password   varchar(255) NOT NULL,
    created_at timestamp    NOT NULL,
    role       varchar(20)  NOT NULL,
    name       varchar(255) NOT NULL,
    CONSTRAINT PK_USERS_ID PRIMARY KEY (id)
);
CREATE UNIQUE INDEX IX_USERS_EMAIL_UNIQ ON users (email);

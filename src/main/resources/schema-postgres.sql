-- USERS
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

-- CATEGORIES
DROP TABLE IF EXISTS categories CASCADE;
DROP SEQUENCE IF EXISTS categories_id_seq;

CREATE SEQUENCE categories_id_seq INCREMENT 1 START 1;
CREATE TABLE categories
(
    id          integer      NOT NULL DEFAULT nextval('categories_id_seq'::regclass),
    name        varchar(255) NOT NULL,
    slug        varchar(255) NOT NULL,
    description varchar(255)          DEFAULT NULL,
    keywords    varchar(255)          DEFAULT NULL,
    parent_id   integer               DEFAULT NULL,
    CONSTRAINT PK_CATEGORIES_ID PRIMARY KEY (id),
    CONSTRAINT FK_CATEGORIES_PARENT_ID FOREIGN KEY (parent_id) REFERENCES categories (id)
        ON UPDATE SET NULL ON DELETE SET NULL
);
CREATE UNIQUE INDEX IX_CATEGORIES_SLUG_UNIQ ON categories (slug);

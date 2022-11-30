CREATE SCHEMA CRS;
SET SCHEMA 'crs';

-- drop table profile;
-- drop table app_user;

CREATE TABLE app_user
(
    id       BIGSERIAL                                    NOT NULL,
    username VARCHAR(30)                                  NOT NULL,
    password VARCHAR(30)
        CONSTRAINT passLen CHECK ( length(password) >= 8) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username, password)
);

CREATE TABLE profile
(
    id         BIGSERIAL,
    first_name VARCHAR(30),
    last_name  VARCHAR(30),
    email      VARCHAR(30),
    birthday   DATE,
    type       VARCHAR(1)
        CONSTRAINT check_allowed CHECK (type in ('S', 'T', 'A')) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES app_user (id)
);

CREATE TABLE program (
    program_id VARCHAR(3) NOT NULL,
    program_name VARCHAR(30),
    PRIMARY KEY (program_id)
);

CREATE TABLE courses (
    course_id VARCHAR(3) NOT NULL,
    course_name VARCHAR(30),
    course_program VARCHAR(3) NOT NULL,
    PRIMARY KEY (course_id),
    FOREIGN KEY (course_program) REFERENCES program(program_id)
);

CREATE TABLE favorite_courses (
    profile BIGSERIAL,
    favorite_course_id VARCHAR(3) NOT NULL,
    FOREIGN KEY (profile) REFERENCES profile(id),
    FOREIGN KEY (favorite_course_id) REFERENCES courses(course_id)
);

-- INSERT ADMIN ACCOUNT
INSERT INTO app_user(username, password)
VALUES ('admin', 'adminadmin');

INSERT INTO profile(id, first_name, last_name, email, birthday, type)
VALUES ((select id from app_user where username = 'admin' and password = 'adminadmin'),
        'Ad', 'min', 'admin@admin.ad', '1993-06-17', 'A');


select * from app_user;
select * from profile;


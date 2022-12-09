SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

GRANT ALL PRIVILEGES ON DATABASE postgres to postgres;

CREATE SCHEMA CRS;
SET SCHEMA 'crs';

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
    id         INTEGER,
    first_name VARCHAR(30),
    last_name  VARCHAR(30),
    email      VARCHAR(30),
    birthday   DATE,
    type       VARCHAR(1)
        CONSTRAINT check_allowed CHECK (type in ('S', 'T', 'A')) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES app_user (id)
);

CREATE TABLE program
(
    short_name VARCHAR(10) NOT NULL,
    name       VARCHAR(50) NOT NULL,
    PRIMARY KEY (short_name)
);

CREATE TABLE course
(
    short_name   VARCHAR(10) NOT NULL,
    name         VARCHAR(50),
    program_name VARCHAR(10) NOT NULL,
    description  VARCHAR(500),
    requirements VARCHAR(500),
    ects         INTEGER     NOT NULL,
    PRIMARY KEY (short_name),
    FOREIGN KEY (program_name) REFERENCES program (short_name)
);



CREATE TABLE favorite_course
(
    profile_id  INTEGER NOT NULL,
    course_name VARCHAR(10),
    FOREIGN KEY (profile_id) REFERENCES profile (id),
    FOREIGN KEY (course_name) REFERENCES course (short_name),
    PRIMARY KEY (profile_id, course_name)
);

CREATE TABLE teacher_of_course
(
    profile_id  INTEGER NOT NULL,
    course_name VARCHAR(10),
    FOREIGN KEY (profile_id) REFERENCES profile (id),
    FOREIGN KEY (course_name) REFERENCES course (short_name),
    PRIMARY KEY (profile_id, course_name)
);

CREATE OR REPLACE FUNCTION check_update_teacher_of_course()
    RETURNS trigger
    LANGUAGE plpgsql
AS
$$
DECLARE
    passed BOOLEAN;
BEGIN
    SELECT (type = 'T')
    INTO passed
    FROM profile
    WHERE id = NEW.profile_id;
    IF NOT passed
    THEN
        RAISE EXCEPTION 'The profile is not a teacher';
    end if;
    RETURN NEW;
END;
$$;

CREATE CONSTRAINT TRIGGER check_update_teacher_of_course_trigger
    AFTER INSERT OR UPDATE OF profile_id
    ON teacher_of_course
    FOR EACH ROW
EXECUTE FUNCTION check_update_teacher_of_course();

CREATE TABLE review
(
    review      VARCHAR(500),
    rate        INTEGER     NOT NULL
        CONSTRAINT rate CHECK ( rate >= 1 AND rate <= 5 ),
    review_date DATE,
    profile_id  INTEGER     NOT NULL,
    course_name VARCHAR(10) NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES profile (id),
    FOREIGN KEY (course_name) REFERENCES course (short_name),
    PRIMARY KEY (profile_id, course_name)
);

-- INSERT ADMIN ACCOUNT
INSERT INTO app_user(username, password)
VALUES ('admin', 'adminadmin'),
       ('prof', 'profprof'),
       ('stud', '00000000');

INSERT INTO profile(id, first_name, last_name, email, birthday, type)
VALUES ((select id from app_user where username = 'admin' and password = 'adminadmin'),
        'Ad', 'min', 'admin@admin.ad', '1993-06-17', 'A'),
       ((select id from app_user where username = 'prof' and password = 'profprof'),
        'prof', 'essor', 'prof@prof.pr', '1993-06-17', 'T'),
       ((select id from app_user where username = 'stud' and password = '00000000'),
        'stud', 'ent', 'stud@stud.st', '1993-06-17', 'S');

INSERT INTO program(short_name, name)
VALUES ('IT', 'Software Technology Engineering');

INSERT INTO course (short_name, name, program_name, description, ects, requirements)
VALUES ('SEP2', 'Semester Project 2', 'IT', '...', 10, '...'),
       ('SDJ2', 'Software Development with UML and Java 2', 'IT', '...', 10, '...');

INSERT INTO teacher_of_course(profile_id, course_name)
VALUES ((select id from app_user where username = 'prof' and password = 'profprof'), 'SEP2'),
       (2, 'SDJ2');

INSERT INTO review(review, rate, review_date, profile_id, course_name)
VALUES ('...', 2, '10-10-2022', (select id from app_user where username = 'stud' and password = '00000000'), 'SEP2');

INSERT INTO favorite_course (profile_id, course_name)
VALUES (1, 'SEP2');

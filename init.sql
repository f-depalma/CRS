CREATE SCHEMA CRS;
SET SCHEMA 'crs';

-- drop table profile;
-- drop table app_user;
-- drop table favorite_course;
-- drop table course;
-- drop table program;


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

<<<<<<< HEAD
CREATE TABLE program
(
    short_name VARCHAR(10),
    name       VARCHAR(50),
    PRIMARY KEY (short_name)
);

CREATE TABLE course
(
    short_name   VARCHAR(10),
    name         VARCHAR(50),
    program_name VARCHAR(10),
    PRIMARY KEY (short_name),
    FOREIGN KEY (program_name) REFERENCES program (short_name)
);

CREATE TABLE favorite_course
(
    profile_id  INTEGER,
    course_name VARCHAR(10),
    FOREIGN KEY (profile_id) REFERENCES profile (id),
    FOREIGN KEY (course_name) REFERENCES course (short_name),
    PRIMARY KEY (profile_id, course_name)
=======
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
>>>>>>> 7c196de4b9f481cf1fdc6e571009829e4f2bbd43
);

-- INSERT ADMIN ACCOUNT
INSERT INTO app_user(username, password)
VALUES ('admin', 'adminadmin');

INSERT INTO profile(id, first_name, last_name, email, birthday, type)
VALUES ((select id from app_user where username = 'admin' and password = 'adminadmin'),
        'Ad', 'min', 'admin@admin.ad', '1993-06-17', 'A');

INSERT INTO program(short_name, name)
VALUES ('IT', 'Software Technology Engineering');

INSERT INTO course (short_name, name, program_name)
VALUES ('SEP2', 'Semester Project 2', 'IT'),
       ('SDJ2', 'Software Development with UML and Java 2', 'IT');

INSERT INTO favorite_course (profile_id, course_name)
VALUES (1, 'SEP2');


select *
from app_user;

select *
from profile;

select *
from course;

select * from course where short_name in ('SEP2');

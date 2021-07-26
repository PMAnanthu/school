--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS student;

CREATE TABLE IF NOT EXISTS student
(
    uuid uuid NOT NULL,
    active boolean,
    address character varying(255) COLLATE pg_catalog."default",
    admission_for character varying(255) COLLATE pg_catalog."default",
    admission_number character varying(255) COLLATE pg_catalog."default",
    blood_group integer,
    cast_details character varying(255) COLLATE pg_catalog."default",
    date_of_admission date,
    date_of_birth date,
    email character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default",
    gender character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    middle_name character varying(255) COLLATE pg_catalog."default",
    only_female_child boolean,
    parent character varying(255) COLLATE pg_catalog."default",
    religion character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT student_pkey PRIMARY KEY (uuid),
    CONSTRAINT uk_t405ejyqvybyy9iw9all2jiin UNIQUE (admission_number)
)
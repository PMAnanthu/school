--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS staff;

CREATE TABLE IF NOT EXISTS staff
(
    uuid uuid NOT NULL,
    active boolean NOT NULL,
    address character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    middle_name character varying(255) COLLATE pg_catalog."default",
    mobile_number character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT staff_pkey PRIMARY KEY (uuid)
)

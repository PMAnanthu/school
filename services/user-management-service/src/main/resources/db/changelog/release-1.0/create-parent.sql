--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS parent;

CREATE TABLE IF NOT EXISTS parent
(
    uuid uuid NOT NULL,
    address character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    middle_name character varying(255) COLLATE pg_catalog."default",
    mobile character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT parent_pkey PRIMARY KEY (uuid)
)
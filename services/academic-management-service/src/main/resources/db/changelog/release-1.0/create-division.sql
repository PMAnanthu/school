--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS division;

CREATE TABLE IF NOT EXISTS division
(
    uuid uuid NOT NULL,
    active boolean NOT NULL,
    batch character varying(255) COLLATE pg_catalog."default",
    division character varying(255) COLLATE pg_catalog."default",
    grade character varying(255) COLLATE pg_catalog."default",
    in_charge uuid,
    CONSTRAINT division_pkey PRIMARY KEY (uuid)
)


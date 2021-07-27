--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS school;

CREATE TABLE IF NOT EXISTS school
(
    uuid uuid NOT NULL,
    address character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    mobile character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    web_site character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT school_pkey PRIMARY KEY (uuid)
)

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
    user_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT staff_pkey PRIMARY KEY (uuid),
    CONSTRAINT uk_7qatq4kob2sr6rlp44khhj53g UNIQUE (user_id),
    CONSTRAINT uk_pvctx4dbua9qh4p4s3gm3scrh UNIQUE (email)
)
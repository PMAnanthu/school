--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS exam;


CREATE TABLE IF NOT EXISTS exam
(
    uuid uuid NOT NULL,
    division uuid,
    out_off double precision,
    scheduled_time timestamp without time zone,
    subject character varying(255) COLLATE pg_catalog."default",
    term character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT exam_pkey PRIMARY KEY (uuid)
)
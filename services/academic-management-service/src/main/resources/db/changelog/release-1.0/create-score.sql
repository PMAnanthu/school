--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS score;

CREATE TABLE IF NOT EXISTS score
(
    uuid uuid NOT NULL,
    exam uuid,
    grade character varying(255) COLLATE pg_catalog."default",
    score double precision,
    status character varying(255) COLLATE pg_catalog."default",
    student uuid,
    CONSTRAINT score_pkey PRIMARY KEY (uuid)
)


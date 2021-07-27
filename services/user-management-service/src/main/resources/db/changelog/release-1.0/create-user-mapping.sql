--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS user_mapping;

CREATE TABLE IF NOT EXISTS user_mapping
(
    uuid uuid NOT NULL,
    login uuid,
    school uuid,
    user_id uuid,
    user_type integer,
    CONSTRAINT user_mapping_pkey PRIMARY KEY (uuid)
)


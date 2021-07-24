--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS login_user;

CREATE TABLE IF NOT EXISTS public.login_user
(
    uuid uuid NOT NULL,
    account_non_expired boolean,
    account_non_locked boolean,
    credentials_non_expired boolean,
    enabled boolean,
    new_user boolean,
    password character varying(255) COLLATE pg_catalog."default",
    school_code character varying(255) COLLATE pg_catalog."default",
    user_name character varying(255) COLLATE pg_catalog."default",
    user_type integer,
    CONSTRAINT login_user_pkey PRIMARY KEY (uuid),
    CONSTRAINT uk_dh17efd8amw7dp61nhqhiswju UNIQUE (user_name)
)
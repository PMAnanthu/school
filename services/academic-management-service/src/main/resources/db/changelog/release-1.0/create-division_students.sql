--liquibase formatted sql

--changeset author:ananthupm:1
DROP TABLE IF EXISTS division_students;

CREATE TABLE IF NOT EXISTS division_students
(
    division_uuid uuid NOT NULL,
    students uuid,
    CONSTRAINT fkdoejnu6uofcut41koxyixihxc FOREIGN KEY (division_uuid)
        REFERENCES public.division (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
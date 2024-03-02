--liquibase formatted sql

--changeset artem:1
create table faculty(
    id bigserial primary key ,
    name text not null unique
);
--changeset artem:2
create table chair(
    id bigserial primary key ,
    name text not null unique
);
--changeset artem:3
create table students_groups(
    id bigserial primary key ,
    name text not null unique
);

--changeset artem:4
create table users(
    id bigserial primary key ,
    first_name text not null ,
    last_name text not null ,
    password text not null ,
    email text not null unique ,
    students_groups bigserial  references students_groups(id),
    chair bigint  references chair(id),
--     course int ,
    role text not null ,
    image text
);




--liquibase formatted sql


--changeset artem:1
create table chair
(
    id   bigserial primary key,
    name text not null unique
);
--changeset artem:2
create table students_groups
(
    id   bigserial primary key,
    name text not null unique
);

--changeset artem:3
create table users
(
    id              bigserial primary key,
    first_name      text not null,
    last_name       text not null,
    password        text not null,
    email           text not null unique,
    students_groups bigserial references students_groups (id),
    chair           bigint references chair (id),
    role            text not null,
    image           text
);

--changeset artem:4
create table subject
(
    id      bigserial primary key,
    name    text not null
);

--changeset artem:5
create table rating
(
    id         bigserial primary key,
    users_id   bigint references users (id),
    subject_id bigint references subject (id),
    value float not null
);

--changeset artem:6
create table global_task(
    id bigserial primary key ,
    name text not null ,
    description text not null ,
    teacher bigint references users(id),
    students_groups bigint references students_groups(id)
);

-- create table laba_task(
--     id bigserial primary key ,
--     name text not null ,
--     description text not null ,
--     teacher bigint references users(id),
--     students_groups bigint references students_groups(id)
-- );

--changeset artem:7
create table task(
    id bigserial primary key ,
    question text not null ,
    value text not null ,
    test bigint references global_task(id)
);

--changeset artem:8
create table true_value(
    id bigserial primary key ,
    task bigint references task(id),
    value text not null ,
    isTrue boolean not null
);

--changeset artem:9
create table visiting(
    id bigserial primary key ,
    max_tasks int not null ,
    current_tasks int
);




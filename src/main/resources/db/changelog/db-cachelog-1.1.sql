-- --liquibase formatted sql

--changeset artem:1
create table notification(
    id bigserial primary key ,
    students_groups bigint references students_groups(id),
    teacher bigint references users(id),
    message text not null
);
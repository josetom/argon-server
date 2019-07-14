create schema if not exists argon_common;

create table if not exists argon_common.USERS (
    ID serial not null primary key,
    EMAIL varchar(100) not null,
    PASSWORD varchar(100) not null
);
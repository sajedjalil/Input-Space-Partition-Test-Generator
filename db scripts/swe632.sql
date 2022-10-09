create database IF NOT EXISTS swe632;
create table IF NOT EXISTS users
(
    user_id int not null auto_increment ,
    first_name  varchar(25) null,
    middle_name varchar(25) null,
    last_name   varchar(25) null,
    email       varchar(25) null,
    password    varchar(50) null,
    constraint user_id
        primary key (user_id)
);

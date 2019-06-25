create schema inno;

create table inno.user (
    id int primary key,
    name varchar(30),
    birthday bigint,
    login_id int,
    city varchar(50),
    email varchar(50),
    description varchar(255)
);

create table inno.role (
    id int primary key,
    name varchar(20),
    description varchar(255)
);

create table inno.user_role (
    id int primary key,
    user_id int,
    role_id int
);

create table inno.logs (
    id serial,
    date timestamp,
    level varchar(10),
    message varchar(255),
    exception varchar
);
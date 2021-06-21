create table products (
    id                      bigserial primary key,
    title                   varchar(255) not null,
    price                   int not null
);

insert into products (title, price)
values
('Milk', 80),
('Bread', 25);

create table universities (id bigserial primary key, title varchar(255));

insert into universities (title) values
('university1'),
('university2'),
('university3');

create table groups (id bigserial primary key, title varchar(255), university_id bigint references universities (id));

insert into groups (title, university_id) values
('group1', 1),
('group2', 1);

create table students (id bigserial primary key, name varchar(255), group_id bigint references groups (id));

insert into students (name, group_id) values
('Bob', 1),
('Jack', 1),
('John', 1);
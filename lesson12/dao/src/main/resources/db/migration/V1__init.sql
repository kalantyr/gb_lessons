create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

create table authorities (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

CREATE TABLE roles_authorities (
  role_id               bigint not null,
  authority_id          int not null,
  primary key (role_id, authority_id),
  foreign key (role_id) references roles (id),
  foreign key (authority_id) references authorities (id)
);

insert into roles (name)
values
('ROLE_TEACHER'), -- Учитель
('ROLE_STUDENT'), -- Школьник
('ROLE_PARENT'); -- Родитель

insert into users (username, password, email)
values
('Марь Ивановна', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user1@gmail.com'),
('Вовочка', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user2@gmail.com'),
('Иван Иванов', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user3@gmail.com'),
('Елена Иванова', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user4@gmail.com');

insert into authorities (name)
values
('LessonVew'), -- Право видеть список уроков
('GradeView'), -- Право видеть оценки
('SubmitHomework'), -- Право сдать домашнюю работу (есть только у Ученика)
('GiveGrade'); -- Право дать оценку домашней работе (есть только у Учителя)

insert into users_roles (user_id, role_id)
values
(1, 1), -- Марь Ивановна (Учитель)
(2, 2), -- Вовочка - (Ученик)
(3, 3), (4, 3); -- Иван и Елена (Родитель)

insert into roles_authorities (role_id, authority_id)
values
(1, 1), (1, 2), (1, 4), -- права Учителя
(2, 1), (2, 2), (2, 3), -- права Ученика
(3, 1), (3, 2); -- права Родителя

create table users
(
    id   int primary key generated by default as identity,
    name varchar(100) not null,
    age  int check (age < 150)
);

create table items
(
    id int primary key generated by default as identity,
    title varchar(100) not null,
    user_id int references users(id) on delete set null
);

insert into users(name, age) VALUES ('Tom', 35);
insert into users(name, age) VALUES ('Bob', 52);
insert into users(name, age) VALUES ('Katy', 14);

insert into items(user_id, title) VALUES (1, 'Book');
insert into items(user_id, title) VALUES (1, 'AirPods');
insert into items(user_id, title) VALUES (2, 'IPhone');
insert into items(user_id, title) VALUES (3, 'Kindle');
insert into items(user_id, title) VALUES (3, 'TV');
insert into items(user_id, title) VALUES (3, 'PlayStation');
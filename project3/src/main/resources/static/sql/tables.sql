create table sensor
(
    id         int primary key generated by default as identity,
    name       varchar(30) not null unique,
    created_at timestamp   not null
);

create table measurement
(
    id         int primary key generated by default as identity,
    value      numeric                    not null check (value >= -100 and value <= 100),
    raining    boolean                    not null,
    created_at timestamp                  not null,
    sensor_id  int references sensor (id) not null
);
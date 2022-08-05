create table person
(
    id            int generated by default as identity
        primary key,
    fcs           varchar(255) not null
        unique,
    year_of_birth int check (year_of_birth >= 1900)
);

create table book
(
    id               int generated by default as identity
        primary key,
    title            varchar(100) not null,
    author           varchar(100) not null,
    year_of_creation int check (year_of_creation >= 1500),
    person_id        int          references person (id) on delete set null,
    taken_at         timestamp
);
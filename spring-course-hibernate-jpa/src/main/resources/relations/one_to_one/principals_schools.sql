CREATE TABLE principals
(
    id   int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(100) NOT NULL,
    age  int CHECK (age > 18)
);

CREATE TABLE schools
(
    id            int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    school_number int        NOT NULL,
    principal_id  int UNIQUE REFERENCES principals (id) ON DELETE SET NULL
);


INSERT INTO principals(name, age)
VALUES ('Bob', 28);
INSERT INTO principals(name, age)
VALUES ('Tom', 35);
INSERT INTO principals(name, age)
VALUES ('Katy', 39);
INSERT INTO principals(name, age)
VALUES ('Alice', 45);

INSERT INTO schools(school_number, principal_id)
VALUES (7, 3);
INSERT INTO schools(school_number, principal_id)
VALUES (131, 1);
INSERT INTO schools(school_number, principal_id)
VALUES (18, 2);
INSERT INTO schools(school_number, principal_id)
VALUES (39, 4);
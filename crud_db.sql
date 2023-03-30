DROP database cruddb;
DROP user crud;
CREATE user crud WITH password 'password';
CREATE database cruddb WITH template=template0 owner=crud;
\connect cruddb;

ALTER DEFAULT privileges grant all on tables to crud;
ALTER DEFAULT privileges grant all on sequences to crud;

CREATE table crud_users(
    user_id INTEGER PRIMARY KEY NOT NULL,
    first_name varchar(20) NOT NULL,
    last_name varchar(20) NOT NULL,
    email varchar(30) NOT NULL,
    password text NOT NULL
);

CREATE table user_licenses(
    license_id INTEGER PRIMARY KEY NOT NULL,
    user_id INTEGER NOT NULL,
    title varchar(20) NOT NULL,
    description varchar(50) NOT NULL
);

ALTER table user_licenses ADD CONSTRAINT crud_users_fk
FOREIGN KEY (user_id) REFERENCES crud_users(user_id);

CREATE sequence crud_users_seq increment 1 start 1;
CREATE sequence user_licenses_seq increment 1 start 1;
drop table person;

create table person
(
id integer not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
vorname varchar(50),
nachname varchar(50),
titel varchar(10),
strasse varchar(80),
plz varchar(10),
ort varchar(80),
land varchar(50),
telefon varchar(20),
email varchar(30)
);
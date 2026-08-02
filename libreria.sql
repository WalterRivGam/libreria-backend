create database libreria;

use libreria;

create table libro (
	id int not null auto_increment,
    titulo varchar(100) not null,
    autor varchar(100) not null,
    precio decimal(6,2) not null,
    primary key (id)
);

insert into libro (titulo, autor, precio) values
('Harry Potter y la piedra filosofal', 'Rowling, J. K.', 59.25),
('Antología del Terror', 'Lovecraft, H.P.', 48.75),
('El Perro de los Baskerville', 'Conan Doyle, Arthur', 29.93);
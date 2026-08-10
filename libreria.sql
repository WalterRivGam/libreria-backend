create database libreria;

use libreria;

create table libro (
	id int not null auto_increment,
    titulo varchar(100) not null,
    autor varchar(100) not null,
    precio decimal(6,2) not null,
    primary key (id)
);

create table usuario (
	id int not null auto_increment,
    username varchar(100) not null unique,
    password varchar(100) not null,
    enabled boolean not null,
    rol varchar(100) not null,
    primary key(id)
);

create table refresh_token (
    id BIGINT not null auto_increment,
    token varchar(255) not null unique,
    fecha_expiracion datetime not null,
    usuario_id int not null,
    foreign key (usuario_id) references usuario(id),
    primary key (id)
);

insert into libro (titulo, autor, precio) values
('Harry Potter y la piedra filosofal', 'Rowling, J. K.', 59.25),
('Antología del Terror', 'Lovecraft, H.P.', 48.75),
('El Perro de los Baskerville', 'Conan Doyle, Arthur', 29.93);
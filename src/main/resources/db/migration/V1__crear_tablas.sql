create table if not exists libro (
	id int not null auto_increment,
    titulo varchar(100) not null,
    autor varchar(100) not null,
    precio decimal(6,2) not null,
    primary key (id)
);

create table if not exists usuario (
	id int not null auto_increment,
    username varchar(100) not null unique,
    password varchar(100) not null,
    enabled boolean not null,
    rol varchar(100) not null,
    primary key(id)
);

create table if not exists refresh_token (
    id BIGINT not null auto_increment,
    token varchar(255) not null unique,
    fecha_expiracion datetime not null,
    usuario_id int not null,
    foreign key (usuario_id) references usuario(id),
    primary key (id)
);
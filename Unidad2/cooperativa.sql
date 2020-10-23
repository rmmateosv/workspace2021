-- Creamos la bd
drop database if exists cooperativa;
create database cooperativa;
use cooperativa;

-- Creación de tablas
create table socio(
	nif varchar(9) primary key not null,
    nombre varchar(100) not null,
    fechaAlta date not null,
    saldo float default 0 not null,
    baja boolean default false not null
)engine Innodb;

insert into socio values 
('1A', 'José Pérez', curdate(), default, default),
('2A', 'Marta Sánchez', curdate(), default, default),
('3A', 'Luis Martín', curdate(), default, default);

create table fruta(
	codigo int auto_increment primary key not null,
    nombre varchar(100) not null,
    fechaIT date not null,
    numAlmacen int null
)engine Innodb;

insert into fruta values 
(null, 'Aceituna verdeo', 20201001, 1),
(10, 'Castaña roja', '2020-11-01', 2),
(null, 'Pistacho', 20201015, 3);

create table entrega(
	codigo int auto_increment primary key not null,
    socio varchar(9) not null,
    fruta int not null,
    fecha date not null,
    kilos float not null,
    precio float not null,
    foreign key(socio) references socio(nif)
		on update cascade
        on delete restrict,
	foreign key(fruta) references fruta(codigo)
		on update cascade
        on delete restrict
)engine Innodb;




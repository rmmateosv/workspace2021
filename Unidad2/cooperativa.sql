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




-- Creación de rutinas
-- Cambiar el delimitador de mysql
delimiter //

-- Creamos procedimiento
create procedure estadistica(p_nif varchar(9))
begin
	select e.fruta, f.nombre, count(*), sum(kilos), avg(precio)
		from entrega e join fruta f on e.fruta = f.codigo
        where e.socio = p_nif
        group by e.fruta;
			
end//


-- Creamos función 
-- Devuelve 1, si borra el socio
-- Devuelve -1 si hay entregas para un socio
create function borrar_socio(p_nif varchar(9)) returns int deterministic
begin
	declare numE int default 0;
	-- Chequear si hay entregas
    select count(*) 
		into numE
		from entrega
        where socio = p_nif;
	if(numE>0) then 
		-- Hay entregas, finaliza con -1
		return -1;
    else
		delete from socio
			where nif = p_nif;
		return 1;
    end if;
    
end//








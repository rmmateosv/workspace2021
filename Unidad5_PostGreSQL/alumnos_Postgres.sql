-- Creamos el tipo de datos estructurado dirección
create type direccion as(
	tipoV varchar(10),
	nombreV varchar(50),
	numero int,
	cp int
);
-- Creamos tabla personans
create table personas(
	codigo serial primary key,
	nombre varchar(50),
	direccion direccion
);

-- Creamos tabla alumnos que hereda de personas
create table alumnos(
	fechaM date,
	primary key(codigo)
)inherits (personas);

insert into alumnos values
 (default,'Pedro',('C','Extremadura',12,10300),'2020-09-01'),
 (default,'Jorge',('Avda','Extremadura',23,10300),'2020-09-01'),
 (default,'Ana',('C','La luna',122,10300),'2020-09-01'),
 (default,'María',('C','El sol',1,10300),'2020-09-01');

-- Creamos tabla profesores que hereda de personas
create table profesores(
	especialidad varchar(50),
	primary key(codigo)
)inherits (personas);

insert into profesores values
 (default,'Luis',('C','Antonio Concha',12,10300),'Web'),
 (default,'Lucía',('Avda','Andalucía',23,10300),'Programación'),
 (default,'Pepe',('Avda','La playa',122,10300),'Sistemas'),
 (default,'Lola',('Avda','La montaña',1,10300),'Redes');
 
create table asig(
	codigo varchar(10) primary key,
	descrip varchar(50)
);
insert into asig values
	('BD', 'Base de datos'),
	('LM', 'Lenguajes de marcas'),
	('AD', 'Acceso a datos');
create table notas(
	alumno int,
	asig varchar(10),
	notas text[][],
	primary key(alumno,asig),
	foreign key (alumno) references alumnos(codigo),
	foreign key (asig) references asig(codigo)
);
insert into notas values 
(1,'BD',array[array['01-01-2021','9'],array['10-01-2021','10']]),
(1,'LM',array[array['15-01-2021','10']]),
(2,'BD',array[array['01-01-2021','6'],array['10-01-2021','7']]);
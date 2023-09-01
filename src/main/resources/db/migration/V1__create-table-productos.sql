create table productos(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    precio bigint(100) not null,
    descripcion varchar(100) not null,
    cantidad tinyint(100) not null,
    categoria varchar(100) not null,

    primary key(id)

 );
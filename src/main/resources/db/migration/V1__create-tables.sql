create table usuarios (
    id bigint not null auto_increment,
    usuario varchar(100) not null,
    password varchar(255) not null,
    rol varchar(50),
    primer_apellido varchar(100),
    segundo_apellido varchar(100),
    email varchar(100),
    estado tinyint default 1,

	primary key(id)
);

create table cursos (
    id bigint not null auto_increment,
    nombre varchar(200) not null,
    categoria varchar(100),
    activo tinyint default 1,

    primary key(id)
);

create table problemas (
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    problema varchar(5000),
    creacion_fecha datetime not null,
    actualizacion datetime not null,
    estado varchar(100),
    usuario_id bigint not null,
    curso_id bigint not null,

    primary key(id),
    constraint fk_problema_usuario_id foreign key(usuario_id) references usuarios(id),
    constraint fk_problema_curso_id foreign key(curso_id) references cursos(id)

);

create table repuestas (
    id bigint not null auto_increment,
    repuesta varchar(5000),
    creacion_fecha datetime not null,
    actualizacion datetime not null,
    solucion tinyint,
    eliminado tinyint,
    usuario_id bigint not null,
    problema_id bigint not null,

    primary key(id),
    constraint fk_repuesta_usuario_id foreign key(usuario_id) references usuarios(id),
    constraint fk_repuesta_problema_id foreign key(problema_id) references problemas(id)

);
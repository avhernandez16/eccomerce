CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio BIGINT NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    cantidad SMALLINT NOT NULL,
    categoria VARCHAR(100) NOT NULL
);

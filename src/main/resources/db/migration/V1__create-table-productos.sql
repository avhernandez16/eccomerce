
CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    urlImg VARCHAR(350) NOT NULL,
    name VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    section VARCHAR(100) NOT NULL,
    stock VARCHAR(100) NOT NULL,
    precio VARCHAR(100) NOT NULL,
    codigoEAN VARCHAR(100) NOT NULL
);


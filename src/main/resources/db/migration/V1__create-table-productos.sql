
CREATE TABLE productos (
    id BIGINT NOT NULL,
    urlImg VARCHAR(400) NOT NULL,
    name VARCHAR(100) NOT NULL,
    descripcion VARCHAR(800) NOT NULL,
    section VARCHAR(100) NOT NULL,
    stock VARCHAR(100) NOT NULL,
    precio VARCHAR(100) NOT NULL,
    codigoEAN VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);


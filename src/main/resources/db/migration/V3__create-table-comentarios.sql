CREATE TABLE comments (
    id BIGINT NOT NULL,
    createat VARCHAR(100) NOT NULL,
    comment VARCHAR(400) NOT NULL,
    producto_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_producto_comentario FOREIGN KEY (producto_id) REFERENCES productos (id)
);

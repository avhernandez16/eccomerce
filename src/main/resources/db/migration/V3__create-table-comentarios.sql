CREATE TABLE comments(
    id SERIAL PRIMARY KEY,
    createat VARCHAR(100) NOT NULL,
    comment VARCHAR(400) NOT NULL,
    producto_id BIGINT NOT NULL,
    CONSTRAINT fk_producto_comentario
    FOREIGN KEY (producto_id)
    REFERENCES productos (id)
);

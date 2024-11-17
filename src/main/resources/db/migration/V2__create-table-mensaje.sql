CREATE TABLE IF NOT EXISTS mensaje (
    id_mensaje BIGINT AUTO_INCREMENT PRIMARY KEY,
    contenido VARCHAR(800) NOT NULL,
    puntuacion DOUBLE,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_usuario BIGINT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
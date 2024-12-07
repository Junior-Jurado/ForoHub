CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE perfiles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE usuario_perfil (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    CONSTRAINT fk_usuario_perfil_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_usuario_perfil_perfil FOREIGN KEY (perfil_id) REFERENCES perfiles(id)
);

CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria ENUM('BACKEND', 'FRONTEND', 'CLOUD', 'AI', 'BLOCKCHAIN') NOT NULL -- Ajusta los valores según las categorías de tu enum `Categoria`
);

CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ABIERTO', 'ARCHIVADO', 'ELIMINADO', 'PENDIENTE', 'RESUELTO') NOT NULL, -- Ajusta los valores según los posibles de tu enum `Status`
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL,
    CONSTRAINT fk_topico_autor FOREIGN KEY (autor) REFERENCES usuarios(id),
    CONSTRAINT fk_topico_curso FOREIGN KEY (curso) REFERENCES cursos(id)
);

CREATE TABLE respuestas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico BIGINT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    autor BIGINT NOT NULL,
    solucion VARCHAR(255),
    CONSTRAINT fk_respuesta_topico FOREIGN KEY (topico) REFERENCES topicos(id) ON DELETE CASCADE,
    CONSTRAINT fk_respuesta_autor FOREIGN KEY (autor) REFERENCES usuarios(id) ON DELETE CASCADE
);

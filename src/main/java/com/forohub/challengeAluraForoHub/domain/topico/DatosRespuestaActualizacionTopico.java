package com.forohub.challengeAluraForoHub.domain.topico;

import jakarta.validation.Valid;

public record DatosRespuestaActualizacionTopico(
        Long id,
        String titulo,
        String mensaje,
        Long autor,
        Long curso
) {

    public DatosRespuestaActualizacionTopico(@Valid DatosActualizarTopico datos, Long id) {
        this(id, datos.titulo(), datos.mensaje(), datos.autor(), datos.curso());

    }

    public DatosRespuestaActualizacionTopico(DatosRespuestaActualizacionTopico datosRespuesta) {
        this(datosRespuesta.id, datosRespuesta.titulo(), datosRespuesta.mensaje(), datosRespuesta.autor(), datosRespuesta.curso());
    }
}

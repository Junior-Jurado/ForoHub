package com.forohub.challengeAluraForoHub.domain.topico;

import com.forohub.challengeAluraForoHub.domain.respuesta.Respuesta;

import java.time.LocalDateTime;
import java.util.List;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        List<Respuesta> respuestas
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getRespuestas());
    }

}

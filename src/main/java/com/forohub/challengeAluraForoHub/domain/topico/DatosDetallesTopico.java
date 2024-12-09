package com.forohub.challengeAluraForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetallesTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        Long autor,
        Long curso
) {
    public DatosDetallesTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor().getId(), topico.getCurso().getId());
    }
}

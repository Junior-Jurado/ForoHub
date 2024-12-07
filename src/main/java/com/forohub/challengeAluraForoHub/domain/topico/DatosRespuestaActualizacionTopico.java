package com.forohub.challengeAluraForoHub.domain.topico;

public record DatosRespuestaActualizacionTopico(
        Long id,
        String titulo,
        String mensaje,
        Status status
) {
}

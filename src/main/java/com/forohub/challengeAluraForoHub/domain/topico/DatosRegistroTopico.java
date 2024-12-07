package com.forohub.challengeAluraForoHub.domain.topico;

import com.forohub.challengeAluraForoHub.domain.curso.Curso;
import com.forohub.challengeAluraForoHub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Long autor,
        @NotNull
        Long curso
) {
}

package com.forohub.challengeAluraForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAll(Pageable paginacion);

    Topico findByTituloAndMensaje(@NotBlank String titulo, String mensaje);
}

package com.forohub.challengeAluraForoHub.domain.curso;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findById(@NotNull Curso curso);
}

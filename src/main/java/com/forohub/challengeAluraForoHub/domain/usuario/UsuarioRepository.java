package com.forohub.challengeAluraForoHub.domain.usuario;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findById(@NotNull Usuario autor);

    UserDetails findByCorreoElectronico(String correoElectronico);
}


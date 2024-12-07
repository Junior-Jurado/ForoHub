package com.forohub.challengeAluraForoHub.domain.respuesta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forohub.challengeAluraForoHub.domain.topico.Topico;
import com.forohub.challengeAluraForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "topico", nullable = false)
    @JsonBackReference
    private Topico topico;
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name = "autor", nullable = false)
    private Usuario autor;
    private String solucion;
}

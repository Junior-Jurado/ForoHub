package com.forohub.challengeAluraForoHub.domain.topico;

import com.forohub.challengeAluraForoHub.domain.curso.Curso;
import com.forohub.challengeAluraForoHub.domain.respuesta.Respuesta;
import com.forohub.challengeAluraForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "autor", nullable = false)
    private Usuario autor;
    @ManyToOne
    @JoinColumn(name = "curso", nullable = false)
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas;

    public Topico() {
    }

    public Topico(@NotBlank String titulo, @NotBlank String mensaje, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.status = Status.ABIERTO;
        this.autor = autor;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Status getStatus() {
        return status;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public List<Respuesta> getRespuestas() {
        if (respuestas == null) {
            return new ArrayList<>();
        }
        return respuestas;
    }

    public void actualizarTopico(@Valid DatosActualizarTopico datos) {
        this.mensaje = datos.mensaje();
        this.status = Status.fromValue(datos.status());
    }
}

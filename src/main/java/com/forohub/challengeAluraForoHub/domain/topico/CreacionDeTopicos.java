package com.forohub.challengeAluraForoHub.domain.topico;

import com.forohub.challengeAluraForoHub.domain.ValidacionException;
import com.forohub.challengeAluraForoHub.domain.curso.CursoRepository;
import com.forohub.challengeAluraForoHub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreacionDeTopicos {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosRespuestaTopico registrar(@Valid DatosRegistroTopico datos) {

        if(!usuarioRepository.existsById(datos.autor())){
            throw new ValidacionException("No existe un usuario con el id informado");
        }

        if(!cursoRepository.existsById(datos.curso())) {
            throw new ValidacionException("No existe un curso con el id informado");
        }
        var autor = usuarioRepository.findById(datos.autor()).get();
        var curso = cursoRepository.findById(datos.curso()).get();
        var topico = new Topico(datos.titulo(), datos.mensaje(), autor, curso);

        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }
}

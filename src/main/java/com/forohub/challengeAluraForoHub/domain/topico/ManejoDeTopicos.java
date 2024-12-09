package com.forohub.challengeAluraForoHub.domain.topico;

import com.forohub.challengeAluraForoHub.domain.ValidacionException;
import com.forohub.challengeAluraForoHub.domain.curso.CursoRepository;
import com.forohub.challengeAluraForoHub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManejoDeTopicos {
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

        if(topicoRepository.findByTituloAndMensaje(datos.titulo(), datos.mensaje()) != null) {
            throw new ValidacionException("Ya existe un topico con el mismo titulo y el mismo mensaje");
        }

        var autor = usuarioRepository.findById(datos.autor()).get();
        var curso = cursoRepository.findById(datos.curso()).get();
        var topico = new Topico(datos.titulo(), datos.mensaje(), autor, curso);

        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }

    public DatosRespuestaActualizacionTopico actualizar(Long id, @Valid DatosActualizarTopico datos) {

        if(topicoRepository.findByTituloAndMensaje(datos.titulo(), datos.mensaje()) != null) {
            throw new ValidacionException("Ya existe un topico con el mismo titulo y el mismo mensaje");
        }

        if(!usuarioRepository.existsById(datos.autor())){
            throw new ValidacionException("No existe un usuario con el id informado");
        }

        if(!cursoRepository.existsById(datos.curso())) {
            throw new ValidacionException("No existe un curso con el id informado");
        }
        if(!topicoRepository.existsById(id)) {
            throw new ValidacionException("No existe un topico con el id informado");
        }
        var autor = usuarioRepository.findById(datos.autor()).get();
        var curso = cursoRepository.findById(datos.curso()).get();
        var topico = topicoRepository.getReferenceById(id);
        topico.actualizarTopico(datos, autor, curso);

        return new DatosRespuestaActualizacionTopico(datos, id);
    }

    public DatosDetallesTopico detalles(Long id) {

        if(!topicoRepository.existsById(id)) {
            throw new ValidacionException("No existe un topico con el id informado");
        }
        var topico = topicoRepository.findById(id).get();
        return new DatosDetallesTopico(topico);
    }
}

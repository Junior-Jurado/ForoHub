package com.forohub.challengeAluraForoHub.controller;

import com.forohub.challengeAluraForoHub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private CreacionDeTopicos crearTopico;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,
                                                                UriComponentsBuilder uriComponentsBuilder) {

        DatosRespuestaTopico respuestaTopico = crearTopico.registrar(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuestaTopico.id()).toUri();
        return ResponseEntity.created(url).body(respuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(@PageableDefault(size = 8) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosRespuestaTopico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaActualizacionTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos) {
        Topico topico = topicoRepository.getReferenceById(datos.id());
        topico.actualizarTopico(datos);
        return ResponseEntity.ok(new DatosRespuestaActualizacionTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

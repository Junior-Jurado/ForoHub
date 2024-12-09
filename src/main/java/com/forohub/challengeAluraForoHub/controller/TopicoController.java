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

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private ManejoDeTopicos manejoTopico;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,
                                                                UriComponentsBuilder uriComponentsBuilder) {

        DatosRespuestaTopico respuestaTopico = manejoTopico.registrar(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuestaTopico.id()).toUri();
        return ResponseEntity.created(url).body(respuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(@PageableDefault(size = 8) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosRespuestaTopico::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaActualizacionTopico> actualizarTopico(@PathVariable Long id,
                                                                              @RequestBody @Valid DatosActualizarTopico datos) {
        var datosRespuesta = manejoTopico.actualizar(id, datos);
        return ResponseEntity.ok(new DatosRespuestaActualizacionTopico(datosRespuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        var datosRespuesta = manejoTopico.detalles(id);

        return ResponseEntity.ok(datosRespuesta);
    }
}

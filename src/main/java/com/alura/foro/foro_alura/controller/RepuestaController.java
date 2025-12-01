package com.alura.foro.foro_alura.controller;

import com.alura.foro.foro_alura.domain.repuesta.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/repuestas")
@SecurityRequirement(name = "bearer-key")
public class RepuestaController {

    @Autowired
    private RepuestaRepository repository;

    @Autowired
    private postearRepuesta postearrepuesta;

    @Transactional
    @PostMapping
    public ResponseEntity registrar (@RequestBody @Valid DatosRegistroRepuesta datos) {
        var problema = postearrepuesta.registrar(datos);

        return ResponseEntity.ok(problema);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaRepuesta>> listar (@PageableDefault(size = 10, sort = {"id"}) Pageable paginacion) {

        var page = repository.findAll(paginacion)
                .map(DatosListaRepuesta::new);

        return ResponseEntity.ok(page);
    }



    @Transactional
    @PutMapping
    public ResponseEntity actualizar (@RequestBody @Valid DatosActualizacionRepuesta datos) {
        var repuesta = repository.getReferenceById(datos.id());
        repuesta.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleRepuesta(repuesta));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity eliminar (@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar (@PathVariable Long id) {
        var repuesta = repository.getReferenceById(id);

        return ResponseEntity.ok(new DatosListaRepuesta(repuesta));
    }

}

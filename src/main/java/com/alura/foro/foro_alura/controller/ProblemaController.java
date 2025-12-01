package com.alura.foro.foro_alura.controller;

import com.alura.foro.foro_alura.domain.problema.*;
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
@RequestMapping("/usuarios/problemas")
@SecurityRequirement(name = "bearer-key")
public class ProblemaController {

    @Autowired
    private ProblemaRepository repository;

    @Autowired
    private postearProblema postearProblema;

    @Transactional
    @PostMapping
    public ResponseEntity registrar (@RequestBody @Valid DatosRegistroProblema datos) {
        System.out.println(datos.titulo());
        var problema = postearProblema.preguntar(datos);

        return ResponseEntity.ok(problema);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaProblema>> listar (@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacion) {

        var page = repository.findAll(paginacion)
                .map(DatosListaProblema::new);

        return ResponseEntity.ok(page);
    }



    @Transactional
    @PutMapping
    public ResponseEntity actualizar (@RequestBody @Valid DatosActualizacionProblema datos) {
        var problema = repository.getReferenceById(datos.id());
        problema.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleProblema(problema));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity eliminar (@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar (@PathVariable Long id) {
        var problema = repository.getReferenceById(id);

        return ResponseEntity.ok(new DatosListaProblema(problema));
    }

}

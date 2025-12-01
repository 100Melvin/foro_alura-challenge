package com.alura.foro.foro_alura.domain.problema;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;


public interface ProblemaRepository extends JpaRepository<Problema, Long> {

    Page<Problema> findAll(Pageable paginacion);

    boolean existsByUsuarioIdAndTitulo(@NotNull Long aLong, @NotNull String titulo);

}

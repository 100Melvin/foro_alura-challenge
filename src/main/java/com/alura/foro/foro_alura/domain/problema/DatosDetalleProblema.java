package com.alura.foro.foro_alura.domain.problema;

import java.time.LocalDateTime;

public record DatosDetalleProblema(
    Long id,
    String titulo,
    String dificultad,
    LocalDateTime Fecha,
    Long Usuario,
    Long Curso
) {
    public DatosDetalleProblema(Problema problema) {
        this(problema.getId(), problema.getTitulo(), problema.getDificultad(), problema.getFecha(), problema.getUsuario().getId(),
                problema.getCurso().getId());
    }

}

package com.alura.foro.foro_alura.domain.problema;

import com.alura.foro.foro_alura.domain.curso.Curso;

import java.time.LocalDateTime;

public record DatosListaProblema(
        Long id,
        String titulo,
        String dificultad,
        LocalDateTime Fecha,
        LocalDateTime FechaActualizacion,
        String estado,
        String Usuario,
        String Curso
) {

    public DatosListaProblema(Problema problema) {
        this(problema.getId().longValue(), problema.getTitulo(), problema.getDificultad(),
                problema.getFecha(), problema.getActualizacion(), problema.getEstado(),
                problema.getUsuario().getNombre(),
                problema.getCurso().getNombre()
        );
    }
}

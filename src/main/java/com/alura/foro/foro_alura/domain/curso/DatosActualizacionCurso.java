package com.alura.foro.foro_alura.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionCurso(
        @NotNull Long id,
        String nombre
) {
}

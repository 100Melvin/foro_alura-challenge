package com.alura.foro.foro_alura.domain.problema;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionProblema(
        @NotNull Long id,
        String titulo,
        String dificultad
) {
}

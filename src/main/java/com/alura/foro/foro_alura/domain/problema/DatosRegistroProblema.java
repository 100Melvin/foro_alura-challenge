package com.alura.foro.foro_alura.domain.problema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroProblema(
        @NotNull Long idUsuario,
        @NotNull Long idCurso,
        @NotNull String titulo,
        @NotNull String dificultad
) {
}

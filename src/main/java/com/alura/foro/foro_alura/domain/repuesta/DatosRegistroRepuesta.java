package com.alura.foro.foro_alura.domain.repuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRepuesta(
        @NotBlank String repuesta,
        @NotNull Long idUsuario,
        @NotNull Long idProblema
) {
}

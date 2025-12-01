package com.alura.foro.foro_alura.domain.repuesta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionRepuesta(
        @NotNull Long id,
        String repuesta
) {
}

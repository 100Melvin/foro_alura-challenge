package com.alura.foro.foro_alura.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionUsuario(
        @NotNull Long id,
        @NotBlank String nombre,
        Tipo tipo,
        String primerApellido,
        String segundoApellido,
        @Email String email
) {
}

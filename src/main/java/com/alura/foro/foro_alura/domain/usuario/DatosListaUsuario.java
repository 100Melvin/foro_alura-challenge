package com.alura.foro.foro_alura.domain.usuario;

public record DatosListaUsuario(
        Long id,
        String nombre,
        Tipo tipo,
        String primerApellido,
        String segundoApellido,
        String email
) {

    public DatosListaUsuario (Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getTipo(),
                usuario.getPrimerApellido(),
                usuario.getSegundoApellido(),
                usuario.getEmail()
        );
    }
}

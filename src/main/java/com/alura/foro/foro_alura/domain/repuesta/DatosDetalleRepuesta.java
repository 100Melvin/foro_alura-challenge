package com.alura.foro.foro_alura.domain.repuesta;

import java.time.LocalDateTime;

public record DatosDetalleRepuesta(
        Long id,
        String repuesta,
        LocalDateTime fecha,
        LocalDateTime actualizacion,
        boolean solucion,
        String usuario,
        String problema
) {


    public DatosDetalleRepuesta(Repuesta repuesta) {
        this(repuesta.getId(), repuesta.getRepuesta(), repuesta.getCreacionFecha(), repuesta.getActualizacion(),
                repuesta.isSolucion(), repuesta.getUsuario().getNombre(), repuesta.getProblema().getTitulo());
    }
}

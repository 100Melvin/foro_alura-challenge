package com.alura.foro.foro_alura.domain.repuesta.validaciones;

import com.alura.foro.foro_alura.domain.ValidacionException;
import com.alura.foro.foro_alura.domain.problema.DatosRegistroProblema;
import com.alura.foro.foro_alura.domain.problema.ProblemaRepository;
import com.alura.foro.foro_alura.domain.problema.validaciones.ValidadorDeProblemas;
import com.alura.foro.foro_alura.domain.repuesta.DatosRegistroRepuesta;
import com.alura.foro.foro_alura.domain.repuesta.RepuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioConMismaRepuestaRepitido implements ValidadorDeRepuestas {

    @Autowired
    private RepuestaRepository repository;


    public void validar(DatosRegistroRepuesta datos) {

        var usuarioTieneLaMismaRepuesta = repository.existsByUsuarioIdAndRepuesta(datos.idUsuario(),
                datos.repuesta());
        if (usuarioTieneLaMismaRepuesta){
            throw new ValidacionException("Puedes agregar una o mas repuestas al mismo problema, pero no la misma," +
                    "" +
                    " que sea aunque sea una palabra diferente");
        }

    }
}

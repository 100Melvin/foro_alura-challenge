package com.alura.foro.foro_alura.domain.problema.validaciones;

import com.alura.foro.foro_alura.domain.ValidacionException;
import com.alura.foro.foro_alura.domain.problema.DatosRegistroProblema;
import com.alura.foro.foro_alura.domain.problema.ProblemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioConElMismoProblemaRepitido implements ValidadorDeProblemas{

    @Autowired
    private ProblemaRepository problemaRepository;


    public void validar(DatosRegistroProblema datos) {

        var usuarioTieneElMismoProblema = problemaRepository.existsByUsuarioIdAndTitulo(datos.idUsuario(),
                datos.titulo());
        if (usuarioTieneElMismoProblema){
            throw new ValidacionException("ya preguntaste por ese mismo problema, verifica si te dijeron " +
                    "la repuesta o espera que te contesten");
        }

    }
}

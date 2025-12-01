package com.alura.foro.foro_alura.domain.repuesta.validaciones;

import com.alura.foro.foro_alura.domain.ValidacionException;
import com.alura.foro.foro_alura.domain.problema.DatosRegistroProblema;
import com.alura.foro.foro_alura.domain.problema.ProblemaRepository;
import com.alura.foro.foro_alura.domain.problema.validaciones.ValidadorDeProblemas;
import com.alura.foro.foro_alura.domain.repuesta.DatosRegistroRepuesta;
import com.alura.foro.foro_alura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorProblemaExistente implements ValidadorDeRepuestas {

    @Autowired
    private ProblemaRepository problemaRepository;

    public void validar(DatosRegistroRepuesta datos){

        var UsuarioActivo = problemaRepository.existsById(datos.idProblema());

        if (!UsuarioActivo){
            throw new ValidacionException("Invalido, tiene que ser un problema existente");
        }
    }
}

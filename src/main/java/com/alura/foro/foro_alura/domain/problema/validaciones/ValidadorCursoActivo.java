package com.alura.foro.foro_alura.domain.problema.validaciones;

import com.alura.foro.foro_alura.domain.ValidacionException;
import com.alura.foro.foro_alura.domain.curso.CursoRepository;
import com.alura.foro.foro_alura.domain.problema.DatosRegistroProblema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCursoActivo implements ValidadorDeProblemas {

    @Autowired
    private CursoRepository cursoRepository;

    public void validar(DatosRegistroProblema datos){

        var cursoActivo = cursoRepository.existsById(datos.idCurso());

        if (!cursoActivo){
            throw new ValidacionException("El problema no puede ser con un curso que no existe");
        }
    }

}

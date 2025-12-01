package com.alura.foro.foro_alura.domain.problema;

import com.alura.foro.foro_alura.domain.ValidacionException;
import com.alura.foro.foro_alura.domain.curso.Curso;
import com.alura.foro.foro_alura.domain.curso.CursoRepository;
import com.alura.foro.foro_alura.domain.problema.validaciones.ValidadorDeProblemas;
import com.alura.foro.foro_alura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class postearProblema {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProblemaRepository repository;

    @Autowired
    private List<ValidadorDeProblemas> validadores;

    public DatosDetalleProblema preguntar(DatosRegistroProblema datos) {

        if (datos.idUsuario() == null && !usuarioRepository.existsById(datos.idUsuario())) {
            throw new ValidacionException("No existe un usuario con el id ingresado");
        }

        if (datos.idCurso() == null && !cursoRepository.existsById(datos.idCurso())) {
            throw new ValidacionException("No existe un curso con el id ingresado");
        }

//        Validaciones
        validadores.forEach(v -> v.validar(datos));


        var curso = cursoElegido(datos);
        var usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        var tutulo = datos.titulo();
        var dificultad = datos.dificultad();
        var problema = new Problema(null, usuario, curso, datos);
        repository.save(problema);
        return new DatosDetalleProblema(problema);
    }

    private Curso cursoElegido(DatosRegistroProblema datos){
        if (datos.idCurso() == null){
            throw new ValidacionException("Es necesario elegir un curso para la pregunta que quieres hacer");
        }
        return cursoRepository.getReferenceById(datos.idCurso());
    }

}

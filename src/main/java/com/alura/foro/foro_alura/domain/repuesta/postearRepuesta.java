package com.alura.foro.foro_alura.domain.repuesta;

import com.alura.foro.foro_alura.domain.ValidacionException;
import com.alura.foro.foro_alura.domain.problema.ProblemaRepository;
import com.alura.foro.foro_alura.domain.problema.validaciones.ValidadorDeProblemas;
import com.alura.foro.foro_alura.domain.repuesta.validaciones.ValidadorDeRepuestas;
import com.alura.foro.foro_alura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class postearRepuesta {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProblemaRepository problemaRepository;

    @Autowired
    private RepuestaRepository repository;


    @Autowired
    private List<ValidadorDeRepuestas> validadores;

    public DatosDetalleRepuesta registrar(DatosRegistroRepuesta datos) {

    if (datos.idUsuario() == null && !usuarioRepository.existsById(datos.idUsuario())) {
        throw new ValidacionException("No existe un usuario con el id ingresado");
    }

        if (datos.idProblema() == null && !problemaRepository.existsById(datos.idProblema())) {
        throw new ValidacionException("No existe un problema con el id ingresado");
    }

    //Validaciones
        validadores.forEach(v -> v.validar(datos));


    var problemaId = problemaRepository.getReferenceById(datos.idProblema());
    var usuario = usuarioRepository.getReferenceById(datos.idUsuario());
    var repuesta = new Repuesta(null, usuario, problemaId, datos);
        repository.save(repuesta);
        return new DatosDetalleRepuesta(repuesta);
}

}

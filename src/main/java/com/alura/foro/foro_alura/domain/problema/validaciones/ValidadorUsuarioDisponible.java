package com.alura.foro.foro_alura.domain.problema.validaciones;

import com.alura.foro.foro_alura.domain.ValidacionException;
import com.alura.foro.foro_alura.domain.problema.DatosRegistroProblema;
import com.alura.foro.foro_alura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioDisponible implements ValidadorDeProblemas {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(DatosRegistroProblema datos){

        var UsuarioActivo = usuarioRepository.existsById(datos.idUsuario());

        if (!UsuarioActivo){
            throw new ValidacionException("Tienes que ser un usuario existente registrate");
        }
    }
}

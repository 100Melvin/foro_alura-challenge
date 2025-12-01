package com.alura.foro.foro_alura.infra.exceptions;

import com.alura.foro.foro_alura.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarError404 () {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarError400 (MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();

        return ResponseEntity.badRequest().body(errores);
    }


    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity gestionarErrorDevalidacion (ValidacionException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public record DatosErrorValidacion(
            String campo,
            String mensaje
    ) {

        public DatosErrorValidacion(FieldError error){
            this(error.getField(),
                    error.getDefaultMessage());
        }

    }

}

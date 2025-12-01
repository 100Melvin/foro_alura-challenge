package com.alura.foro.foro_alura.domain.curso;

import com.alura.foro.foro_alura.domain.ValidacionException;
import com.alura.foro.foro_alura.domain.problema.Problema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String nombre;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private boolean activo = true;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Problema> problema;

    public Curso(DatosRegistroCurso datos) {
        this.id = null;
        this.nombre = datos.nombre();
        this.categoria = datos.categoria();
        this.activo = true;
    }

    public void actualizarInformaciones(@Valid DatosActualizacionCurso datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
    }

    public void eliminar() {
        this.activo = false;
    }

}

package com.alura.foro.foro_alura.domain.problema;

import com.alura.foro.foro_alura.domain.curso.Curso;
import com.alura.foro.foro_alura.domain.repuesta.Repuesta;
import com.alura.foro.foro_alura.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "problemas")
@Entity(name = "Problema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Problema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(name = "problema")
    private String dificultad;

    @CreationTimestamp
    @Column(name = "creacion_fecha")
    private LocalDateTime fecha;
    @UpdateTimestamp
    private LocalDateTime actualizacion;
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "problema", cascade = CascadeType.ALL)
    private List<Repuesta> repuesta;


    public Problema(Long id, Usuario usuario, Curso curso, @Valid DatosRegistroProblema datos) {
            this.id = id;
            this.usuario = usuario;
            this.curso = curso;
            this.titulo = datos.titulo();
            this.dificultad = datos.dificultad();

    }

    public void actualizarInformaciones(@Valid DatosActualizacionProblema datos) {
        if (datos.titulo() != null){
            this.titulo = datos.titulo();
        }
        if (datos.dificultad() != null){
            this.dificultad = datos.dificultad();
        }
    }

}

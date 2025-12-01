package com.alura.foro.foro_alura.domain.repuesta;

import com.alura.foro.foro_alura.domain.problema.Problema;
import com.alura.foro.foro_alura.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "repuestas")
@Entity(name = "Repuesta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Repuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String repuesta;
    @CreationTimestamp
    @Column(name = "creacion_fecha")
    private LocalDateTime creacionFecha = LocalDateTime.now();
    @UpdateTimestamp
    private LocalDateTime actualizacion = LocalDateTime.now();
    private boolean solucion = true;
    private boolean eliminado = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problema_id")
    private Problema problema;

    public Repuesta(Long id, Usuario usuario, Problema problemaId, @Valid DatosRegistroRepuesta datos) {
        this.id = id;
        this.repuesta = datos.repuesta();
        this.usuario = usuario;
        this.problema = problemaId;
    }

    @PrePersist
    public void actualizarEstadoProblema() {
        if (problema != null){
            problema.setEstado("respondido");
        }
    }

    public void actualizarInformaciones(@Valid DatosActualizacionRepuesta datos) {
        if (datos.id() != null && datos.repuesta() != null){
            this.repuesta = datos.repuesta();
        }
    }
}

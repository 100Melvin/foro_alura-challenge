package com.alura.foro.foro_alura.domain.usuario;

import com.alura.foro.foro_alura.domain.problema.Problema;
import com.alura.foro.foro_alura.domain.repuesta.Repuesta;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario")
    private String nombre;
    private String password;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private boolean estado = true;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Problema> problemas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Repuesta> repuestas;


    public Usuario(@Valid DatosRegistroUsuario datos) {
        this.id = null;
        this.nombre = datos.nombre();
       this.password = datos.password();
        this.tipo = datos.tipo();
        this.primerApellido = datos.primerApellido();
        this.segundoApellido = datos.segundoApellido();
        this.email = datos.email();
        this.estado = true;
    }

    public void actualizarInformaciones(@Valid DatosActualizacionUsuario datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
            this.tipo = datos.tipo();
            this.primerApellido = datos.primerApellido();
            this.segundoApellido = datos.segundoApellido();
            this.email = datos.email();
        }
    }

    public void eliminar() {
        this.estado = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

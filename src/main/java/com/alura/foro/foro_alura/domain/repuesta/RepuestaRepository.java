package com.alura.foro.foro_alura.domain.repuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepuestaRepository extends JpaRepository<Repuesta, Long> {

    boolean existsByUsuarioIdAndRepuesta(@NotNull Long aLong, @NotBlank String repuesta);
}

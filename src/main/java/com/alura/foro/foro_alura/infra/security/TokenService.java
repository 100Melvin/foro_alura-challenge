package com.alura.foro.foro_alura.infra.security;

import com.alura.foro.foro_alura.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario) {

        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API foro_alura")
                    .withSubject(usuario.getNombre())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);


        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al generar el token JWT", exception);
        }

    }

    private Instant fechaExpiracion() {
//        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
        return LocalDateTime.now().plusHours(2)
                .atZone(ZoneId.systemDefault()).toInstant();

    }

    // para obtener el subject del token
    public String getSubject(String tokenJWT) {

        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API foro_alura")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("token JWT invalido o expirado");

        }
    }

    public void inspecionarToken(String Token) {
        DecodedJWT decodedJWT = JWT.decode(Token);

        System.out.println("Issuer: " + decodedJWT.getIssuer());
        System.out.println("Subject: " + decodedJWT.getSubject());
        System.out.println("Expira en: " + decodedJWT.getExpiresAt());

    }

}

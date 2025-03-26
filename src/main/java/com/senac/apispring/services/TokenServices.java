
package com.senac.apispring.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.senac.apispring.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServices {

    @Value("senac")
    private String secret;

    public String gerarToken(User usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC512(secret);
            return JWT.create()
                    .withIssuer("senac")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC512(secret);
            return JWT.require(algoritmo)
                    .withIssuer("senac")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }
    public boolean isValid(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC512(secret);
            JWT.require(algoritmo)
                    .withIssuer("senac")
                    .build()
                    .verify(tokenJWT);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusMinutes(5).toInstant(ZoneOffset.of("-03:00"));
    }
}

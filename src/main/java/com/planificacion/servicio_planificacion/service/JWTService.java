package com.planificacion.servicio_planificacion.service;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planificacion.servicio_planificacion.config.SecurityConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    @Autowired
    private SecurityConfig securityConfig;

    private SecretKey obtenerSecretKey() {
        String secret = securityConfig.jwtSecret;

        if (secret == null || "none".equals(secret)) {
            throw new RuntimeException("JWT Secret no configurado");
        }

        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);

        if (keyBytes.length < 32) {
            byte[] paddedKey = new byte[32];
            for (int i = 0; i < 32; i++) {
                paddedKey[i] = keyBytes[i % keyBytes.length];
            }
            keyBytes = paddedKey;
        } else if (keyBytes.length > 32) {
            byte[] truncatedKey = new byte[32];
            System.arraycopy(keyBytes, 0, truncatedKey, 0, 32);
            keyBytes = truncatedKey;
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims validarToken(String token) {
        try {
            SecretKey key = obtenerSecretKey();

            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (Exception e) {
            throw new RuntimeException("Token inválido o expirado: " + e.getMessage());
        }
    }

    public String obtenerEmailDelToken(String token) {
        Claims claims = validarToken(token);
        return claims.getSubject();
    }
}
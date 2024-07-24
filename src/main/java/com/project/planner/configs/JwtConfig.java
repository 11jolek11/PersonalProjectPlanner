package com.project.planner.configs;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {

    private SecretKey getSignInKey() {
        String decodeBase = System.getenv("DEFAULT_SIGN_IN_SECRET_KEY");
        if (decodeBase == null) {
            throw new RuntimeException("Bad decode base");
        }
        byte[] secretString = Decoders.BASE64.decode(decodeBase);
        return Keys.hmacShaKeyFor(secretString);
    }

    @Bean
    public JwtParser createJWTParser() {
        return Jwts.parser().verifyWith(getSignInKey()).build();
    }

    @Bean
    public JwtBuilder createJwtBuilder() {
        return Jwts.builder();
    }
}

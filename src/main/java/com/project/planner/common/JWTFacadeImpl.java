package com.project.planner.common;

import com.project.planner.IJWTFacade;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JWTFacade implements IJWTFacade {

    @Override
    public JwtParser createJWTParser(SecretKey signInKey) {
        return Jwts.parser().verifyWith(signInKey).build();
    }

    @Override
    public JwtBuilder getJwtBuilder() {
        return Jwts.builder();
    }
}

package com.project.planner;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;


public interface IJWTFacade {
    public JwtParser createJWTParser(SecretKey signInKey);
    public JwtBuilder getJwtBuilder();
}

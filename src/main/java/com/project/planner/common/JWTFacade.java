package com.project.planner.common;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;

import javax.crypto.SecretKey;


public interface JWTFacade {
    public JwtParser createJWTParser(SecretKey signInKey);
    public JwtBuilder getJwtBuilder();
}

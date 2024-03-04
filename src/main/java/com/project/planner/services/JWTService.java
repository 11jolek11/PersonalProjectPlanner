package com.project.planner.services;

import com.project.planner.exceptions.AuthException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTService {

    private final JwtParser jwtParser = Jwts.parser()
                                            .verifyWith(getSignInKey())
                                            .build();
    private final JwtBuilder jwtBuilder  = Jwts.builder();

    @Value("${jwt.token.expiration-time}")
    private Integer tokenExpirationTime;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Optional<Claims> extractAllClaims(String token) {
        Optional<Claims> claims;
        try {
            claims = Optional.of(jwtParser.parseSignedClaims(token).getPayload());
        }

        catch (JwtException ex) {
            throw new AuthException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return claims;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token).orElseThrow(
                () -> new AuthException(HttpStatus.INTERNAL_SERVER_ERROR)
        );

        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {

        return  jwtBuilder
                .subject(userDetails.getUsername())
                .issuer("Planner")
                .claims(extraClaims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpirationTime))

                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private SecretKey getSignInKey() {
        byte[] secretString = Decoders.BASE64.decode(System.getenv("DEFAULT_SIGN_IN_SECRET_KEY"));
        return Keys.hmacShaKeyFor(secretString);
    }

    private SecretKey getEncryptionKey() {
        byte[] secretString = Decoders.BASE64.decode(System.getenv("DEFAULT_ENCRYPT_SECRET_KEY"));
        return Keys.hmacShaKeyFor(secretString);
    }
}

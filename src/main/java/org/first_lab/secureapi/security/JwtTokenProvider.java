package org.first_lab.secureapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecretKey;

    private static final long TOKEN_VALIDITY_DURATION = 86400000;
    private static final String ISSUER = "secure-task-manager-api";

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(jwtSecretKey);
    }

    public String generateAccessToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + TOKEN_VALIDITY_DURATION);

        return JWT.create()
                .withSubject(username)
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(getAlgorithm());
    }

    public String extractUsername(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            verifyToken(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    private DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(getAlgorithm())
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }
}
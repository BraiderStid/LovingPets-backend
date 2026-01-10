package com.lovingpets.appointment_service.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "mY$up3rS3cR3tK3yForLovingPets2026!";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public boolean isValid(String jwt) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM).build();
            verifier.verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public List<String> getRoles(String jwt) {

        JWTVerifier verifier = JWT.require(ALGORITHM).build();

        DecodedJWT decoded = verifier.verify(jwt);

        return decoded.getClaim("roles").asList(String.class);
    }

    public Long getUserId(String jwt) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        DecodedJWT decoded = verifier.verify(jwt);
        return decoded.getClaim("userId").asLong();
    }

}
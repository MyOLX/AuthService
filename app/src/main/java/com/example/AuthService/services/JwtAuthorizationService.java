package com.example.AuthService.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.AuthService.constants.AuthorizationConstants;
import com.example.AuthService.constants.CommonConstants;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtAuthorizationService implements AuthorizationService {
    private final String JWT_ISSUER;
    private final String SECRET_KEY;

    protected JwtAuthorizationService() {
        JWT_ISSUER = "auth0";
        SECRET_KEY = getJwtSecretKey();
    }

    @Override
    public String createAuthorizationToken(String uniqueId) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            long newExpirationTimeMillis = System.currentTimeMillis() + AuthorizationConstants.TOKEN_EXPIRY_TIME;
            return JWT.create()
                .withIssuer(JWT_ISSUER)
                .withClaim(CommonConstants.UNIQUE_ID, uniqueId)
                .withExpiresAt(new Date(newExpirationTimeMillis))
                .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new Exception("Could not create JWT token");
        }
    }

    @Override
    public String getUniqueIdFromToken(String token) throws Exception {
        try {
            DecodedJWT decodedJWT = verifyJwtToken(token);
            return decodedJWT.getClaim(CommonConstants.UNIQUE_ID).asString();
        } catch (JWTVerificationException exception){
            throw new Exception("Error in validating JWT token");
        }
    }

    @Override
    public long getExpiresAtFromToken(String token) throws Exception {
        try {
            DecodedJWT decodedJWT = verifyJwtToken(token);
            return decodedJWT.getExpiresAt().getTime();
        } catch (JWTVerificationException exception){
            throw new Exception("Error in validating JWT token");
        }
    }

    @Override
    public String refreshToken(String token) throws Exception {
        DecodedJWT decodedJWT = verifyJwtToken(token);
        Date now = new Date();
        Date expiresAt = decodedJWT.getExpiresAt();
        long minutesDiff = TimeUnit.MILLISECONDS.toMinutes(expiresAt.getTime() - now.getTime());
        if( minutesDiff < 20 )
            return createAuthorizationToken(decodedJWT.getClaim(CommonConstants.UNIQUE_ID).toString());
        return token;
    }

    private DecodedJWT verifyJwtToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(JWT_ISSUER)
                .build();
        return verifier.verify(token);
    }

    private String getJwtSecretKey() {
        return "super_secret_key"; // TODO improve algo to get secret key
    }
}

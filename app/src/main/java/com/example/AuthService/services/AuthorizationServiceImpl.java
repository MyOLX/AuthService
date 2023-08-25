package com.example.AuthService.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.AuthService.constants.AuthorizationConstants;

public class AuthorizationServiceImpl implements AuthorizationService {
    private final AuthorizationService authorizationService;

    public AuthorizationServiceImpl(String authType) throws Exception {
        switch (authType) {
            case AuthorizationConstants.AUTHORIZATION_TYPE_JWT, "" -> {
                authorizationService = new JwtAuthorizationService();
            }
            default -> {
                throw new Exception("Unsupported authorization type");
            }
        }
    }

    @Override
    public String createAuthorizationToken(String uniqueId) throws Exception {
        return authorizationService.createAuthorizationToken(uniqueId);
    }

    @Override
    public String getUniqueIdFromToken(String token) throws Exception {
        return authorizationService.getUniqueIdFromToken(token);
    }

    @Override
    public long getExpiresAtFromToken(String token) throws Exception {
        return authorizationService.getExpiresAtFromToken(token);
    }

    @Override
    public String refreshToken(String token) throws Exception {
       return authorizationService.refreshToken(token);
    }
}

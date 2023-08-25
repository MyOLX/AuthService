package com.example.AuthService.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.AuthService.models.LoginResponseModel;

public interface AuthorizationService {
    public String createAuthorizationToken(String uniqueId) throws Exception;
    public String getUniqueIdFromToken(String token) throws Exception;
    public long getExpiresAtFromToken(String token) throws Exception;
    public String refreshToken(String token) throws Exception;
}

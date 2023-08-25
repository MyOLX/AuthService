package com.example.AuthService.services;

public interface AuthorizationService {
    public String createAuthorizationToken(String uniqueId) throws Exception;
    public String getUniqueIdFromToken(String token) throws Exception;
    public long getExpiresAtFromToken(String token) throws Exception;
    public String refreshToken(String token) throws Exception;
}

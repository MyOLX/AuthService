package com.example.AuthService.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JwtAuthResponseModel extends AuthorizationResponseModel {
    private String jwtToken;
    private long expiresAt;
}

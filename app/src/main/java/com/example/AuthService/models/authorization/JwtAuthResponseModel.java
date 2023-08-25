package com.example.AuthService.models.authorization;

import com.example.AuthService.models.authorization.AuthorizationResponseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JwtAuthResponseModel extends AuthorizationResponseModel {
    private String jwtToken;
    private long expiresAt;
}

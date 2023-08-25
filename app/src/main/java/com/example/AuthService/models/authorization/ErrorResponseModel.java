package com.example.AuthService.models.authorization;

import lombok.Data;

@Data
public abstract class ErrorResponseModel {
    private String errMessage;
}

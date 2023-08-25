package com.example.AuthService.models;

import lombok.Data;

@Data
public abstract class ErrorResponseModel {
    private String errMessage;
}

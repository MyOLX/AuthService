package com.example.AuthService.models;

import lombok.Data;

@Data
public class LoginResponseModel {
    private String unique_id;
    private String errMessage;
}

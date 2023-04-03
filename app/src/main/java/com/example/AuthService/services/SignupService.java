package com.example.AuthService.services;

import java.util.List;

import com.example.AuthService.models.SignupModel;

public interface SignupService {
    public String validateUsername(String username);
    public String validatePassword(String password, String reEnteredPassword);
    public List<String> getMandatoryFieldList();
    public String validateMandatoryFields(SignupModel signupModel) throws Exception;
    public void handleSignup(SignupModel signupModel) throws Exception;
}

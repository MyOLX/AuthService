package com.example.AuthService.services;

import java.util.List;

import com.example.AuthService.models.signup.SignupModel;

public interface SignupService {
    public void validateUsername(String username) throws Exception;
    public void validatePassword(String password, String reEnteredPassword) throws Exception;
    public List<String> getMandatoryFieldList();
    public void validateMandatoryFields(SignupModel signupModel) throws Exception;
    public void handleSignup(SignupModel signupModel) throws Exception;
}

package com.example.AuthService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AuthService.models.SignupModel;
import com.example.AuthService.services.SignupService;

@RestController
@ResponseBody
public class SignupController {

    @Autowired
    private SignupService signupService;

    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity<String> signupNewUser(@RequestBody SignupModel signupModel) throws Exception {
        try {
            signupService.validateUsername(signupModel.getUsername());
            signupService.validatePassword(signupModel.getPassword(), signupModel.getRePassword());
            signupService.validateMandatoryFields(signupModel);
        } catch(Exception e) {
            return new ResponseEntity<>("Error: "+e.toString(), HttpStatus.BAD_REQUEST);
        }
        
        try {
            signupService.handleSignup(signupModel);
            return new ResponseEntity<>("Signup successful!", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("Error: "+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

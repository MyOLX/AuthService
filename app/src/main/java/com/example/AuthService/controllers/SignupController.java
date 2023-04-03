package com.example.AuthService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/signup")
    public ResponseEntity<String> signupNewUser(@RequestBody SignupModel signupModel) throws Exception {
        
        System.out.println(signupModel.toString());

        String err = signupService.validateUsername(signupModel.getUsername());
        if( err != null ) {
            return new ResponseEntity<>("Error: "+err, HttpStatus.BAD_REQUEST);
        }

        err = signupService.validatePassword(signupModel.getPassword(), signupModel.getRe_password());
        if( err != null ) {
            return new ResponseEntity<>("Error: "+err, HttpStatus.BAD_REQUEST);
        }
        
        err = signupService.validateMandatoryFields(signupModel);
        if( err != null ) {
            return new ResponseEntity<>("Error: "+err, HttpStatus.BAD_REQUEST);
        }
        
        try {
            signupService.handleSignup(signupModel);
            return new ResponseEntity<>("Signup successful!", HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>("Error: "+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

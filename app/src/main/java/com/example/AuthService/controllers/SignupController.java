package com.example.AuthService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AuthService.models.SignupModel;
import com.example.AuthService.models.SignupResponseModel;
import com.example.AuthService.services.SignupService;

@RestController
@RequestMapping("/api/v1/signup")
@ResponseBody
public class SignupController {

    @Autowired
    private SignupService signupService;

    @CrossOrigin
    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<SignupResponseModel> signupNewUser(@RequestBody SignupModel signupModel) throws Exception {
        SignupResponseModel response = new SignupResponseModel();
        
        try {
            signupService.validateUsername(signupModel.getUsername());
            signupService.validatePassword(signupModel.getPassword(), signupModel.getRePassword());
            signupService.validateMandatoryFields(signupModel);
        } catch(Exception e) {
            response.setErrMessage(e.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
        try {
            signupService.handleSignup(signupModel);

            response.setUsername(signupModel.getUsername());
            response.setName(signupModel.getFirstName()+" "+signupModel.getLastName());
            response.setPhone(signupModel.getPhone());
            response.setEmail(signupModel.getEmail());
            response.setDateOfBirth(signupModel.getDateOfBirth());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch(Exception e) {
            response.setErrMessage(e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

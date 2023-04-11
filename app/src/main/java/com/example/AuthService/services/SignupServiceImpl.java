package com.example.AuthService.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AuthService.entities.AuthData;
import com.example.AuthService.models.SignupModel;
import com.example.AuthService.utils.ObjMapperUtil;
import com.example.AuthService.utils.PasswordUtil;

@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    private AuthDataRepositoryHandler repo;

    public void validateUsername(String username) throws Exception {
        if(username == null || username.equals(""))
            throw new Exception("username cannot be empty");

        if(username.length() > 10)
            throw new Exception("username too big");

        if( usernameExists(username) )
            throw new Exception("username already exists");
    }

    public void validatePassword(String password, String reEnteredPassword) throws Exception {
        if(!password.equals(reEnteredPassword)) {
            throw new Exception("re-entered password does not match");
        }

        if(password == null || password.equals("")) {
            throw new Exception("password cannot be empty");
        }

        validPassword(password);
    }

    public List<String> getMandatoryFieldList() {
        List<String> mandatoryFields = new ArrayList<>();
    
        //mandatory fields - add here
        mandatoryFields.add("firstName");
        mandatoryFields.add("phone");
        mandatoryFields.add("email");

        return mandatoryFields;
    }

    public void validateMandatoryFields(SignupModel signupModel) throws Exception {
        List<String> mandatoryFieldsList = getMandatoryFieldList();

        Map<String, Object> mandatoryFieldsMap = new HashMap<>();
        Map<String, Object> fieldsMap = ObjMapperUtil.convertToMap(signupModel);
        for(String key: mandatoryFieldsList) {
            mandatoryFieldsMap.put(key, fieldsMap.get(key));
        }
        
        for(Map.Entry<String, Object> field: mandatoryFieldsMap.entrySet()) {
            if(field.getValue() == null || field.getValue().equals("")) {
                throw new Exception("missing mandatory fields "+field.getKey());
            }
        }
    }

    public void handleSignup(SignupModel signupModel) throws Exception{
        // Encode password to store in database
        signupModel.setPassword(
            PasswordUtil.getEncodedPassword(
                signupModel.getUsername(), 
                signupModel.getPassword()
            )
        );
        repo.save(mapModelToEntity(signupModel));
    }

    private boolean usernameExists(String username) {
        if( repo.existsById(username) )
            return true;
        return false;
    }

    private void validPassword(String password) throws Exception {
        // TODO
    }

    private AuthData mapModelToEntity(SignupModel signupModel) {
        AuthData authData = new AuthData();
            
        authData.setUsername(signupModel.getUsername());
        authData.setFirstName(signupModel.getFirstName());
        authData.setLastName(signupModel.getLastName());
        authData.setPhone(signupModel.getPhone());
        authData.setEmail(signupModel.getEmail());
        authData.setPassword(signupModel.getPassword());
        authData.setCreatedAt(java.time.LocalDateTime.now().toString());

        return authData;
    }
}

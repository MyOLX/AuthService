package com.example.AuthService.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.AuthService.entities.AuthData;
import com.example.AuthService.models.SignupModel;
import com.example.AuthService.utils.ObjMapperUtil;
import com.example.AuthService.utils.PasswordUtil;

@Service
public class SignupServiceImpl implements SignupService {
    public String validateUsername(String username) {
        if(username == null || username.equals(""))
            return "username cannot be empty";

        if(username.length() > 10)
            return "username too big";

        if( usernameExists(username) )
            return "username already exists";

        return null;
    }

    public String validatePassword(String password, String reEnteredPassword) {
        if(!password.equals(reEnteredPassword)) {
            return "re-entered password does not match";
        }

        if(password == null || password.equals("")) {
            return "password cannot be empty";
        }

        String err = validPassword(password);
        if( err != null ) {
            return err;
        }

        return null;
    }

    public List<String> getMandatoryFieldList() {
        List<String> mandatoryFields = new ArrayList<>();
    
        //mandatory fields - add here
        mandatoryFields.add("first_name");
        mandatoryFields.add("phone");
        mandatoryFields.add("email");

        return mandatoryFields;
    }

    public String validateMandatoryFields(SignupModel signupModel) throws Exception {
        List<String> mandatoryFieldsList = getMandatoryFieldList();

        Map<String, Object> mandatoryFieldsMap = new HashMap<>();
        Map<String, Object> fieldsMap = ObjMapperUtil.convertToMap(signupModel);
        for(String key: mandatoryFieldsList) {
            mandatoryFieldsMap.put(key, fieldsMap.get(key));
        }
        
        for(Map.Entry<String, Object> field: mandatoryFieldsMap.entrySet()) {
            if(field.getValue() == null || field.getValue().equals("")) {
                return "missing mandatory fields "+field.getKey();
            }
        }
        return null;
    }

    public void handleSignup(SignupModel signupModel) throws Exception{
        // Encode password to store in database
        signupModel.setPassword(
            PasswordUtil.getEncodedPassword(
                signupModel.getUsername(), 
                signupModel.getPassword()
            )
        );
        AuthDataRepositoryHandler.save(mapModelToEntity(signupModel));
    }

    private boolean usernameExists(String username) {
        if( AuthDataRepositoryHandler.existsById(username) )
            return true;
        return false;
    }

    private String validPassword(String password) {
        // TODO
        return null;
    }

    private AuthData mapModelToEntity(SignupModel signupModel) {
        AuthData authData = new AuthData();
            
        authData.setUsername(signupModel.getUsername());
        authData.setFirst_name(signupModel.getFirst_name());
        authData.setLast_name(signupModel.getLast_name());
        authData.setPhone(signupModel.getPhone());
        authData.setEmail(signupModel.getEmail());
        authData.setPassword(signupModel.getPassword());

        return authData;
    }
}

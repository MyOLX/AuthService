package com.example.AuthService.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

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

        String username = signupModel.getUsername();
        String password = PasswordUtil.getEncodedPassword(username, signupModel.getPassword());

        System.out.println("----------------------------");
        System.out.println(username+"   "+password);
        System.out.println("----------------------------");

    }

    private boolean usernameExists(String username) {
        // TODO
        return false;
    }

    private String validPassword(String password) {
        // TODO
        return null;
    }
}

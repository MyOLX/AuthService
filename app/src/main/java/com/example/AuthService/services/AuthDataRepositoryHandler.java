package com.example.AuthService.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.AuthService.entities.AuthData;
import com.example.AuthService.repositories.AuthDataRepository;

public class AuthDataRepositoryHandler {
    
    @Autowired
    private static AuthDataRepository authDataRepository;

    private static boolean isIdValid(String id) {
        if( id == null || id == "" )
            return false;
        return true;
    }

    static void save(AuthData authData) throws Exception {
        try {
            authDataRepository.save(authData);
        } catch(Exception e) {
            throw new Exception("unable to save data in repository Error-> "+e.toString());
        }
    }

    static AuthData findById(String id) {
        if( !isIdValid(id) )
            return null;

        Optional<AuthData> authDataOpt = authDataRepository.findById(id);
        if( !authDataOpt.isPresent() )
            return null;
        if( authDataOpt.isEmpty() )
            return null;
        return authDataOpt.get();
    }

    static boolean existsById(String id) {
        if( isIdValid(id) )
            return false;
        return authDataRepository.existsById(id);
    }

}

package com.example.AuthService.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AuthService.entities.AuthData;
import com.example.AuthService.repositories.AuthDataRepository;

@Service
public class AuthDataRepositoryHandler {
    
    @Autowired
    private AuthDataRepository authDataRepository;

    private boolean isIdValid(String id) {
        if( id == null || id == "" )
            return false;
        return true;
    }

    void save(AuthData authData) throws Exception {
        try {
            authDataRepository.save(authData);
        } catch(Exception e) {
            throw new Exception("unable to save data in repository Error-> "+e.toString());
        }
    }

    AuthData findById(String id) {
        if( !isIdValid(id) )
            return null;

        Optional<AuthData> authDataOpt = authDataRepository.findById(id);
        if( !authDataOpt.isPresent() )
            return null;
        if( authDataOpt.isEmpty() )
            return null;
        return authDataOpt.get();
    }

    void update(AuthData updatedAuthData) {
        try {
            authDataRepository.save(updatedAuthData);
        } catch(Exception e) {
            throw new Error("unable to update the data Error: "+e.toString());
        }
    }

    boolean existsById(String id) {
        if( isIdValid(id) )
            return false;
        return authDataRepository.existsById(id);
    }

    void delete(AuthData authData) {
        try {
            authDataRepository.delete(authData);
        } catch(Exception e) {
            throw new Error("unable to delete the data Error: "+e.toString());
        }
    }

}

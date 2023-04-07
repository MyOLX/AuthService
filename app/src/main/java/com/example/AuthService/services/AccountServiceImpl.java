package com.example.AuthService.services;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AuthService.entities.AuthData;
import com.example.AuthService.utils.PasswordUtil;

@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    private AuthDataRepositoryHandler repo;
    
    public void updateUsername(String username, String password, String newUsername) throws Exception {
        password = PasswordUtil.getEncodedPassword(username, password);
        AuthData authData = repo.findById(username);
        if( authData.getPassword().equals(password) ) {
            String newPassword = PasswordUtil.getEncodedPassword(newUsername, password);

            authData.setUsername(newUsername);
            authData.setPassword(newPassword);

            System.out.println(authData);

            try {
                repo.update(authData);
            } catch(Exception e) {
                throw new Exception("Not able to update Error: "+e.toString());
            }
        } else {
            throw new AccessDeniedException("Invalid username/password");
        }
        
    }

    public void updatePassword(String username, String password, String newPassword) throws Exception {
        password = PasswordUtil.getEncodedPassword(username, password);
        AuthData authData = repo.findById(username);
        if( authData.getPassword().equals(password) ) {
            newPassword = PasswordUtil.getEncodedPassword(username, newPassword);

            authData.setPassword(newPassword);

            try {
                repo.update(authData);
            } catch(Exception e) {
                throw new Exception("Not able to update Error: "+e.toString());
            }
        } else {
            throw new AccessDeniedException("Invalid username/password");
        }
    }

    public void updateName(String username, String password, String newFirstName, String newLastName) throws Exception {
        password = PasswordUtil.getEncodedPassword(username, password);
        AuthData authData = repo.findById(username);
        if( authData.getPassword().equals(password) ) {
            authData.setFirstName(newFirstName);
            authData.setLastName(newLastName);
            try {
                repo.update(authData);
            } catch(Exception e) {
                throw new Exception("Not able to update Error: "+e.toString());
            }
        } else {
            throw new AccessDeniedException("Invalid username/password");
        }
    }

    public void updatePhone(String username, String password, String newPhone) throws Exception {
        password = PasswordUtil.getEncodedPassword(username, password);
        AuthData authData = repo.findById(username);
        if( authData.getPassword().equals(password) ) {
            authData.setPhone(newPhone);
            try {
                repo.update(authData);
            } catch(Exception e) {
                throw new Exception("Not able to update Error: "+e.toString());
            }
        } else {
            throw new AccessDeniedException("Invalid username/password");
        }
    }

    public void updateEmail(String username, String password, String newEmail) throws Exception {
        password = PasswordUtil.getEncodedPassword(username, password);
        AuthData authData = repo.findById(username);
        if( authData.getPassword().equals(password) ) {
            authData.setEmail(newEmail);
            try {
                repo.update(authData);
            } catch(Exception e) {
                throw new Exception("Not able to update Error: "+e.toString());
            }
        } else {
            throw new AccessDeniedException("Invalid username/password");
        }
    }

    public void deleteAccount(String username, String password) throws Exception {
        password = PasswordUtil.getEncodedPassword(username, password);
        AuthData authData = repo.findById(username);
        if( authData.getPassword().equals(password) ) {
            try {
                repo.delete(authData);
            } catch(Exception e) {
                throw new Exception("Not able to delete Error: "+e.toString());
            }
        } else {
            throw new AccessDeniedException("Invalid username/password");
        }
    }
}

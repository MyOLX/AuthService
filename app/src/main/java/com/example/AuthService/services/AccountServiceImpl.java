package com.example.AuthService.services;

import java.nio.file.AccessDeniedException;

import com.example.AuthService.models.account.AccountDetailsModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AuthService.entities.AuthData;
import com.example.AuthService.utils.PasswordUtil;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    private AuthDataRepositoryHandler repo;

    @Override
    public AccountDetailsModel getUserDetails(String username) throws Exception {
        AuthData authData = repo.findById(username);
        if( authData == null )
            throw new Exception("Cannot find user with username "+username);
        return new AccountDetailsModel() {{
            setName(authData.getFirstName()+" "+authData.getLastName());
            setEmail(authData.getEmail());
            setPhone(authData.getPhone());
        }};
    }

    @Override
    public void updateUsername(String username, String password, String newUsername) throws Exception {
        password = PasswordUtil.getEncodedPassword(username, password);
        AuthData authData = repo.findById(username);
        if( authData.getPassword().equals(password) ) {
            String newPassword = PasswordUtil.getEncodedPassword(newUsername, password);

            authData.setUsername(newUsername);
            authData.setPassword(newPassword);
            try {
                repo.update(authData);
            } catch(Exception e) {
                throw new Exception("Not able to update Error: "+ e);
            }
        } else {
            throw new AccessDeniedException("Invalid username/password");
        }
        
    }

    @Override
    public void updatePassword(String username, String password, String newPassword) throws Exception {
        password = PasswordUtil.getEncodedPassword(username, password);
        AuthData authData = repo.findById(username);
        if( authData.getPassword().equals(password) ) {
            newPassword = PasswordUtil.getEncodedPassword(username, newPassword);

            authData.setPassword(newPassword);
            try {
                repo.update(authData);
            } catch(Exception e) {
                throw new Exception("Not able to update Error: "+ e);
            }
        } else {
            throw new AccessDeniedException("Invalid username/password");
        }
    }

    @Override
    public void updateName(String username, String newFirstName, String newLastName) throws Exception {
        AuthData authData = repo.findById(username);
        authData.setFirstName(newFirstName);
        authData.setLastName(newLastName);
        try {
            repo.update(authData);
        } catch(Exception e) {
            throw new Exception("Not able to update Error: "+ e);
        }
    }

    @Override
    public void updatePhone(String username, String newPhone) throws Exception {
        AuthData authData = repo.findById(username);
        authData.setPhone(newPhone);
        try {
            repo.update(authData);
        } catch(Exception e) {
            throw new Exception("Not able to update Error: "+ e);
        }
    }

    @Override
    public void updateEmail(String username, String newEmail) throws Exception {
        AuthData authData = repo.findById(username);
        authData.setEmail(newEmail);
        try {
            repo.update(authData);
        } catch(Exception e) {
            throw new Exception("Not able to update Error: "+ e);
        }
    }

    @Override
    public void deleteAccount(String username, String password) throws Exception {
        password = PasswordUtil.getEncodedPassword(username, password);
        AuthData authData = repo.findById(username);
        if( authData.getPassword().equals(password) ) {
            try {
                repo.delete(authData);
            } catch(Exception e) {
                throw new Exception("Not able to delete Error: "+ e);
            }
        } else {
            throw new AccessDeniedException("Invalid username/password");
        }
    }
}

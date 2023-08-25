package com.example.AuthService.services;

import com.example.AuthService.models.account.AccountDetailsModel;

public interface AccountService {
    public AccountDetailsModel getUserDetails(String username) throws Exception;
    public void updateUsername(String username, String password, String newUsername) throws Exception;
    public void updatePassword(String username, String password, String newPassword) throws Exception;
    public void updateName(String username, String newFirstName, String newLastName) throws Exception;
    public void updatePhone(String username, String newPhone) throws Exception;
    public void updateEmail(String username, String newEmail) throws Exception;
    public void deleteAccount(String username, String password) throws Exception;
}

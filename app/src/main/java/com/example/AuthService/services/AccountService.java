package com.example.AuthService.services;

public interface AccountService {
    public void updateUsername(String username, String password, String newUsername) throws Exception;
    public void updatePassword(String username, String password, String newPassword) throws Exception;
    public void updateName(String username, String password, String newFirstName, String newLastName) throws Exception;
    public void updatePhone(String username, String password, String newPhone) throws Exception;
    public void updateEmail(String username, String password, String newEmail) throws Exception;
    public void deleteAccount(String username, String password) throws Exception;
}

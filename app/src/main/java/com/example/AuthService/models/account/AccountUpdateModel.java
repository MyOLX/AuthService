package com.example.AuthService.models.account;

import lombok.Data;

@Data
public class AccountUpdateModel {
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String newUsername;
    private String newFirstName;
    private String newLastName;
    private String newPhone;
    private String newEmail;
    private String newPassword;
}
